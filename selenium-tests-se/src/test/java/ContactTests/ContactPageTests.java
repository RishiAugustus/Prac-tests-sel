package test.java.ContactTests;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.pageobjects.BasePageClass;
import main.java.pageobjects.ContactSalesPage;
import main.java.pageobjects.SchneiderHomePage;
import main.java.pageobjects.components.TopNavigation;
import test.java.Basic.SuiteBaseTemplate;

public class ContactPageTests extends SuiteBaseTemplate {

	@BeforeClass
	public void init(){
		//PageFactory.initElements(driver, SchneiderHomePage.class);
		//PageFactory.initElements(driver, TopNavigation.class);
		PageFactory.initElements(driver, ContactSalesPage.class);
		//Bypassing global url to decrease test execution [Code in progress]
		driver.get("http://software.schneider-electric.com/contact-us/contact-sales/");
	}
	
	@Test(groups="ContactUs-Tests", priority=1)
	public void verifyContactPageTitle(){
		String expectedTitle = Param.getProperty("ContactSalesPageTitle");
		String actualTitle = ContactSalesPage.getPageTitle();
		System.out.println(actualTitle);
		Assert.assertTrue(BasePageClass.verifyPageTitle(expectedTitle, actualTitle));
	}
	
	@Test(groups="ContactUs-Tests", dependsOnMethods="verifyContactPageTitle", priority=2)
	public void TestContactSalesPage() {
		String ProductName = "SimSci";
		ContactSalesPage.selectProduct(ProductName);
		BasePageClass.waitForLoad();
		ContactSalesPage.selectRegion("Latin America");
		BasePageClass.waitForLoad();
		String ProductNames = ContactSalesPage.getProducNameForCountry("Venezuela");
		Assert.assertTrue(ProductNames.toLowerCase().contains(ProductName.toLowerCase()));	
	}
	
}
