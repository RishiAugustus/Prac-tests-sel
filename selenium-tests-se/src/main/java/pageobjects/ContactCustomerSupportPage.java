package main.java.pageobjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ContactCustomerSupportPage extends BasePageClass {

	@FindAll(@FindBy(css=".block-grid.three-up.mobile>li>h3>a"))
	private static List<WebElement> CustomerSupportLinks;
	
	@FindBy(css=".six.columns>h1")
	private static WebElement supportPageHeader;
	
	@FindBy(css=".column.small-centered.medium-8.text-center>h1")
	private static WebElement wonderwareCustomerSupportHeader;
	
	public ContactCustomerSupportPage(WebDriver driver) {
		super(driver);
	}
	
	private static List<WebElement> getCustomerSupportLinks(){
		return CustomerSupportLinks;
	}
	public static void selectCustomerSupportLink(String productName){
		//List<WebElement> customerSupportLinks = driver.findElements(By.cssSelector(".block-grid.three-up.mobile>li>h3>a"));
		List<WebElement> customerSupportLinks = getCustomerSupportLinks();
		for (WebElement lnk : customerSupportLinks)
		{
			if(lnk.getText().toLowerCase().contains(productName.toLowerCase()))
			{
				lnk.click();
			}
			
		}
			
	}
	
	//In case of Wonderware new url appears.
	public static boolean verifyPageHeader(String ProductName)
	{
		boolean isPageHeaderEqual = false;
		try
		{
			if(!"wonderware".equalsIgnoreCase(ProductName))
			{
				String pageHeader = supportPageHeader.getText();
				if(pageHeader.toLowerCase().contains(ProductName.toLowerCase()))
				{
					isPageHeaderEqual = true;
				}
				
			}
			else
			{
				String pageHeader = wonderwareCustomerSupportHeader.getText();
				if(pageHeader.toLowerCase().contains(ProductName.toLowerCase()))
				{
					isPageHeaderEqual = true;
				}
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return isPageHeaderEqual;
		
	}
	
}
