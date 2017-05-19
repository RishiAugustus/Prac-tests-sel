package main.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SchneiderHomePage extends BasePageClass {
	
	@FindBy(css="a[title='Read the Blog']")
	private static WebElement btn_readTheBlog;
	
	public SchneiderHomePage(WebDriver driver) {
		super(driver);
	
	}
	
	public static void goToUrl(String url){
		driver.get(url);
	}
	
	public static String getCurrentUrl(){
		String CurrentUrl = driver.getCurrentUrl();
		return CurrentUrl;
	}
	
	public static void goToBlogPage(){
		
		BasePageClass.waitForElementToBeClickable(btn_readTheBlog);
		btn_readTheBlog.click();
		BasePageClass.switchToNewBrowserTab();
		
	}
	

	

}
