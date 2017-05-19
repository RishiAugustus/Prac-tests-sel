package main.java.driverprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverProvider {
	private WebDriver driver;
	String browser;
	public DriverProvider(String browser){
		this.browser = browser;
	}
	
	public WebDriver getdriver(){
		
		if("Firefox".equalsIgnoreCase(browser)){
			String driverPath = System.getProperty("user.dir") + "//BrowserDrivers//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
		} else if("Chrome".equalsIgnoreCase(browser)){
			String driverPath = System.getProperty("user.dir") + "//BrowserDrivers//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		}
		return driver;
		
	}
	
}
