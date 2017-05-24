package test.java.ContactTests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.pageobjects.BasePageClass;
import main.java.pageobjects.ContactCustomerSupportPage;
import main.java.pageobjects.WonderwareCustomerSupport;
import test.java.Basic.SuiteBaseTemplate;

public class ContactCustomerSupport extends SuiteBaseTemplate {
	
	@BeforeClass
	public void init(){
		//PageFactory.initElements(driver, SchneiderHomePage.class);
		//PageFactory.initElements(driver, TopNavigation.class);
		//PageFactory.initElements(driver, ContactSalesPage.class);
		PageFactory.initElements(driver, ContactCustomerSupportPage.class);
		PageFactory.initElements(driver, WonderwareCustomerSupport.class);
		//Bypassing global url to decrease test execution [Code in progress]
		driver.get("http://software.schneider-electric.com/contact-us/contact-customer-support/");
	}
	
	@Test(groups="Contact-Sales-Support",priority=1, enabled=false)
	public void testLinksAvailableOnPage() throws Exception{
		BasePageClass.verifyLinksOnPage();
	}
	
	@Test(groups="Contact-Sales-Support", priority=2)
	public void verifyContactProductSupport()
	{
		String ProductName = "Wonderware";
		ContactCustomerSupportPage.selectCustomerSupportLink(ProductName);
		Assert.assertTrue(ContactCustomerSupportPage.verifyPageHeader(ProductName));
	}
	
	@Test(groups="Contact-Sales-Support", priority=3)
	public void verifyWonderwareContactDetails(){
		WonderwareCustomerSupport.selectRegionAs("Asia Pacific").selectCountryAs("China");
	}
	
}
