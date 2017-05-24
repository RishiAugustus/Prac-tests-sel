package main.java.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class WonderwareCustomerSupport extends BasePageClass {

	@FindAll(@FindBy(css=".accordion-navigation>a"))
	private static List<WebElement> regionList;
	private static By countryNames = By.xpath("./following-sibling::div//li");
	private Country country;
	
	public WonderwareCustomerSupport(WebDriver driver) {
		super(driver);
	}
	
	public static Country selectRegionAs(String region)
	{
		
		Country country = new Country(region);
		return country;
	}
	
	
	public static class Country{
		private String region;
		
		public Country(String region){
			this.region = region;
		}
		
		public void selectCountryAs(String countryName){
			WebElement regionElem = this.selectRegionName();
			if(regionElem != null){
				List<WebElement> countries = regionElem.findElements(countryNames);
				for(WebElement cntry : countries){
					String cntryName = cntry.getText();
					if(cntryName.toLowerCase().contains(countryName.toLowerCase())){
						System.out.println(cntryName);
					}
				}
			}
		}	
		
		private WebElement selectRegionName(){
			try{
				List<WebElement> regionNames = regionList;
				for(WebElement rgn : regionNames){
					String regionName = rgn.getText();
					if(region.equalsIgnoreCase(regionName)){
						rgn.click();
						return rgn;
					}
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	
	}
	

}
