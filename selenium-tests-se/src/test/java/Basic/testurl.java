package test.java.Basic;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import org.openqa.selenium.support.PageFactory;
import main.java.pageobjects.*;
public class testurl extends SuiteBaseTemplate {
	
	
	SoftAssert softassert = new SoftAssert();
	
	@BeforeClass
	public void init(){
		
		//SchneiderHomePage.goToUrl("http://software.schneider-electric.com/products/simsci/design/dynsim/");
		//driver.get("http://software.schneider-electric.com/products/simsci/design/dynsim/");
		PageFactory.initElements(driver, SchneiderHomePage.class);
		PageFactory.initElements(driver, BlogPage.class);
	}
	
	@Test(priority=1)
	public void checkUrl(){
		//System.out.println("Title: " + SchneiderHomePage.getCurrentUrl());
		System.out.println("Param:" + Param.getProperty("SiteTitle"));
		softassert.assertEquals(Param.getProperty("SiteUrl"), SchneiderHomePage.getCurrentUrl());
	}
	
	
	
}
