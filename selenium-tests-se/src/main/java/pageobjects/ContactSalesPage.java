package main.java.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactSalesPage extends BasePageClass {

	@FindAll(@FindBy(xpath="//input[@class='productLineRadio']/parent::label"))
	private static List<WebElement> radio_productList;
	
	@FindAll(@FindBy(css=".twelve>tbody>tr>td>h4"))
	private static List<WebElement> td_countryNames;
	
	@FindBy(css="#ctl00_MainContentPlaceHolder_Dropzone2_uxColumnDisplay_ctl00_uxControlColumn_ctl00_uxWidgetHost_uxWidgetHost_widget_UxPagination_UxList")
	private static WebElement pageBar;
	
	@FindBy(css="#ctl00_MainContentPlaceHolder_Dropzone2_uxColumnDisplay_ctl00_uxControlColumn_ctl00_uxWidgetHost_uxWidgetHost_widget_UxPagination_UxList>li>a")
	private static List<WebElement> pageElements;
	
	@FindAll(@FindBy(css="#region"))
	private static WebElement dropDown_region;
	 
	private static By lnk_ContactUs = By.cssSelector(".social-contact");
	
	public ContactSalesPage(WebDriver driver) {
		super(driver);
	}
	
	public static List<WebElement> getProductList(){
		return radio_productList;
	}
	
	public static List<WebElement> getCountryList(){
		return td_countryNames;
	}
	
	public static String getPageTitle(){
		return driver.getTitle();
	}
	
	public static void selectRegion(String regionName){
		Select select = new Select(dropDown_region);
		List<WebElement> regions = select.getOptions();
		for(int i = 0; i< regions.size(); i++){
			if(regionName.equalsIgnoreCase(regions.get(i).getText())){
				select.selectByIndex(i);
				break;
			}
		}
			
	}
	
	public static void selectProduct(String ProductName){
		List<WebElement> productElems = getProductList();
		for(WebElement productElem : productElems){
			//System.out.println("Product: " + productElem.getText());
			if(ProductName.equalsIgnoreCase(productElem.getText())){
				productElem.click();
				System.out.println("Element clicked");
				//BasePageClass.implicityWait();
				break;
			}
		}
	}
	
	public static boolean isPageElementPresent(){
		return BasePageClass.isElementPresent(pageBar);
	}
	
	public static int getNoOfPages(){
		
		if(isPageElementPresent()){
			//BasePageClass.moveToElementJs(pageBar);
			return pageElements.size();
		} else{
			return 1;
		}
		
	}
	
	public static void clickOnPageNumeber(int pageNumber){
		//BasePageClass.moveToElementJs(pageBar);
		pageNumber = pageNumber + 1;
		if(pageNumber >= getNoOfPages()){
			System.out.println("Page num:" + pageNumber + " is out of bound" );
			return;
		}
		
		WebElement pageNo = pageElements.get(pageNumber);
		System.out.println("Opening Page:" + pageNo.getText());
		pageNo.click();
		BasePageClass.waitForJStoLoad();
		waitForUrlToChange(pageNo.getText());
	}
	
	public static String getProducNameForCountry(String name){
		
		int pageCount = getNoOfPages();
		System.out.println("Page Count: " + pageCount);
		for (int i = 0; i<pageCount; i++){
			//BasePageClass.implicityWait();	
			WebElement elem = findCountryOnPage(name);
			if(elem != null){
				//System.out.println("Found on:" + i);
				//Make element visible
				BasePageClass.moveToElementJs(elem);
				elem.click();
				//To get enclosed text 
				String ProductString = elem.findElement(By.xpath("./ancestor::div[@class='title']/following-sibling::div/p/em")).getText();
				System.out.println("product string: " + ProductString);
				return ProductString;
				//break;
			} 
			
			clickOnPageNumeber(i);
			
		}
		return "";
	} 
		
	public static WebElement findCountryOnPage(String CountryName){
		//BasePageClass.moveToElementJs(dropDown_region);
		List<WebElement> CountryNames = getCountryList();
		for(WebElement CtryName : CountryNames){
			String name = CtryName.getText();
			//System.out.println("Countryname:" + name);
			if (name.toLowerCase().contains(CountryName.toLowerCase())){
				//System.out.println("Returning country: " + CtryName);
				return CtryName;
			}
		}
		return null;
	}
	
	public static void waitForUrlToChange(String PageNum){
		//int attempts = 0;
		boolean urlChanged = true;
		PageNum = "pageNum=" + PageNum;
		while(urlChanged){
			String currentUrl = driver.getCurrentUrl();
			//System.out.println("Current Url:" + currentUrl);
			//System.out.println("String PageNum: " + PageNum);
			if(currentUrl.contains(PageNum)){
				//System.out.println("Found");
				urlChanged = false;
				break;
			}
			//attempts++;
		}
	}
}
