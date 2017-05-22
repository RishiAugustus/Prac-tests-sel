package main.java.pageobjects;

import static org.testng.Assert.assertFalse;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;





public class BasePageClass {
	public static WebDriver driver;
	protected int timeout = 20;
	public static WebDriverWait wait;
	public static Actions builder;
	public static JavascriptExecutor js;
	
	public BasePageClass(WebDriver driver){
		BasePageClass.driver = driver;
		BasePageClass.wait = new WebDriverWait(driver, timeout);
		BasePageClass.builder = new Actions(driver);
		BasePageClass.js = (JavascriptExecutor)driver;
		//PageFactory.initElements(driver, BasePageClass.class);
	}
	
	public static boolean verifyPageTitle(String expected, String actual){
		
		if(expected.equalsIgnoreCase(actual)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static String switchToNewBrowserTab() {
	    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(tabs.size() - 1));
	    return driver.getCurrentUrl();
	  }
	
	public static void waitForElementToPresent(By locator){
		
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void waitForElementToBeClickable(WebElement element){
		try{
			
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void moveToElementJs(WebElement elem){
		js.executeScript(
		          "arguments[0].scrollIntoView(true);",elem);
	}
	
	//mouse hover and click on subelement
	public static void mouseHoverAction(WebElement element, By by){
		try{
			//mouseHoverJScript(element);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			builder.moveToElement(element).clickAndHold()
			.moveToElement(driver.findElement(by)).click().build().perform();
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//wait.until(ExpectedConditions.elementToBeClickable(by));
			//driver.findElement(by).click();
			//builder.moveToElement(subElement).click().build().perform();
			//builder.moveToElement(driver.findElement(by)).click().perform();
		} catch(Exception e){
			e.printStackTrace();
		} 	
	}
	
	public static void mouseHoverJScript(WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {
				
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, true); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				js.executeScript(mouseOverScript, HoverElement);
				//js.executeScript("arguments[0].onmouseover()", HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}
	
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public static void implicityWait(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void waitForPageLoaded() {
	    ExpectedCondition<Boolean> expectation = new
	    ExpectedCondition<Boolean>() 
	    {
	        public Boolean apply(WebDriver driver)
	        {
	            return (js.executeScript("return document.readyState").equals("complete"));
	        }
	    };
	        wait.until(expectation);
	    
	}
	
	public static void clickOnElementAfterPageRefresh(By locator)
	{
	    //final WebDriverWait wait = new WebDriverWait(driver, timeout);
	    wait.until(ExpectedConditions.refreshed(
	        ExpectedConditions.elementToBeClickable(locator)));
	    //driver.findElement(locator).click();
	}
	
	public static void waitForLoad() {
	    wait.until((ExpectedCondition<Boolean>) driver ->
	            js.executeScript("return document.readyState").equals("complete"));
	}
	
	public static boolean waitForJStoLoad() {

	    // wait for jQuery to load
	   ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)js.executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          return true;
	        }
	      }
	    }; 

	    // wait for Javascript to load
	   ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return js.executeScript("return document.readyState")
	            .toString().equals("complete");
	      }
	    };

	 return wait.until(jQueryLoad) && wait.until(jsLoad);
	   // return wait.until(jsLoad);
	}
	
	public static List<WebElement> findAllLinksOnPage(){
		List<WebElement> elementList = new ArrayList<WebElement>();
		elementList = driver.findElements(By.tagName("a"));
		
		//images may also contain links
		elementList.addAll(driver.findElements(By.tagName("img")));
		
		List<WebElement> linkList = new ArrayList<WebElement>();
		
		for(WebElement elem : elementList)
		{
			String url = elem.getAttribute("href");
			if(url != null && !url.contains("javascript"))
			{
				linkList.add(elem);
			}
		}
		return linkList;
	}
	
	public static String isLinkBroken(URL url) throws Exception
	{
		String response = "";
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		try
		{	
			connection.connect();
			response = connection.getResponseMessage();
			connection.disconnect();
			return response;
		} catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	public static void verifyLinksOnPage() throws Exception{
		List<WebElement> allLinks = findAllLinksOnPage();
		System.out.println("No of Links found on Page: " + allLinks.size());
		
		for(WebElement elem : allLinks)
		{
			URL url = new URL(elem.getAttribute("href"));
			try 
			{
				System.out.println("URL: " + url + " returned: " + isLinkBroken(url));
				
			} catch (Exception e) {
				
				System.out.println("Exception: " + e.getMessage() + "occured at URL: " + url);
			}
			
		}
	}
	
}

