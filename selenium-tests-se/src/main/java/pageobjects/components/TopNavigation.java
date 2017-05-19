package main.java.pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import main.java.pageobjects.BasePageClass;

public class TopNavigation extends BasePageClass {

	@FindBy(xpath=".//*[@id='main_nav']//li/a[text()='Contact Us']")
	private static WebElement lnk_ContactUs;
	
	//@FindBy(xpath=".//*[@id='main_nav']//li//a[text()='Contact Overview']")
	//private static WebElement lnk_ContactUs_ContactOverview;
	static By lnk_ContactUs_Overview = By.xpath(".//*[@id='main_nav']//li//a[text()='Contact Overview']");
	static By lnk_ContactUs_ContactSales = By.xpath(".//*[@id='main_nav']//li/a[text()='Contact Sales']");
	static By lnk_ContactUs_ContactCustomerSupport = By.xpath(".//*[@id='main_nav']//li/a[text()='Contact Customer Support']");
	static By lnk_ContactUs_ContactTraining = By.xpath(".//*[@id='main_nav']//li/a[text()='Contact Training']");
	
	public TopNavigation(WebDriver driver) {
		super(driver);
	}
	
	public static class ContactUs{
		public static void goToContactOverview(){
			
			BasePageClass.mouseHoverAction(lnk_ContactUs, lnk_ContactUs_Overview);
		}
		
		public static void goToContactSales(){
			BasePageClass.mouseHoverAction(lnk_ContactUs, lnk_ContactUs_ContactSales);
			
		}
		
		public static void goToContactCustomerSupport(){
			BasePageClass.mouseHoverAction(lnk_ContactUs, lnk_ContactUs_ContactCustomerSupport);
		}
		
		public static void goToContactTraining(){
			
			BasePageClass.mouseHoverAction(lnk_ContactUs, lnk_ContactUs_ContactTraining);
		}
		
	}

}
