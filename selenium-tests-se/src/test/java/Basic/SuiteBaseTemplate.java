package test.java.Basic;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import main.java.driverprovider.DriverProvider;
import main.java.pageobjects.BasePageClass;
import main.java.pageobjects.SchneiderHomePage;

public class SuiteBaseTemplate {
	public static WebDriver driver;
	public static Properties Param = null;
	
	
	@BeforeSuite
	@Parameters("browser")
	public void getBrowser(String browser){
		DriverProvider driverProvider = new DriverProvider(browser);
		driver = driverProvider.getdriver();
		new BasePageClass(driver);
		
		Param = new Properties();
		
		try {
			
			String testProperties = System.getProperty("user.dir") + "//src//main//java//common//Parameter.properties";
			FileInputStream fip = new FileInputStream(testProperties);
			Param.load(fip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		//PageFactory.initElements(driver, SchneiderHomePage.class);
		//commenting out for time being
		//SchneiderHomePage.goToUrl("http://software.schneider-electric.com/products/simsci/design/dynsim/");
	}
	
	
}
