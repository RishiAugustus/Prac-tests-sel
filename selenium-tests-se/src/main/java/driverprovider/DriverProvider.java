package main.java.driverprovider;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverProvider {
	private WebDriver driver;
	String browser;
	private DesiredCapabilities caps;
	public DriverProvider(String browser){
		this.browser = browser;
		caps = new DesiredCapabilities();
	}
	
	public WebDriver getdriver() throws MalformedURLException{
		
		if("Firefox".equalsIgnoreCase(browser)){
			String driverPath = System.getProperty("user.dir") + "//BrowserDrivers//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath); 
			
			//For grid test
			/*caps = DesiredCapabilities.firefox();
			caps.setPlatform(org.openqa.selenium.Platform.LINUX);
			caps.setCapability("marionette", true); */
			//caps.setCapability("jenkins.label", "FF_DEB");
			driver = new FirefoxDriver();
			
		} else if("Chrome".equalsIgnoreCase(browser)){
			String driverPath = System.getProperty("user.dir") + "//BrowserDrivers//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			
			/* caps = DesiredCapabilities.chrome();
			caps.setPlatform(org.openqa.selenium.Platform.LINUX);
			caps.setVersion("2.29"); */
			//System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium/chromedriver");
			//caps.setCapability("jenkins.label", "CH_WIN");
			driver = new ChromeDriver();
			
		}
		//driver = new RemoteWebDriver(new URL("http://augustus-pc:4444/wd/hub"), caps);
		return driver;
		
	}
	
}
