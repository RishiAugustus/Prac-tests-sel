package main.java.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class SEHomePage extends BasePageClass {
	
	@FindAll(@FindBy(css=".item>a>img"))
	private static List<WebElement> succussStories;
	
	@FindBy(css=".owl-next")
	private static WebElement nextArrowSuccessStories;
	
	public SEHomePage(WebDriver driver) {
		super(driver);
		
	}
	
	public static List<WebElement> getSuccessStories(){
		return succussStories;
	}
	
	public static void checkSuccessStoryScroll(){
		List<WebElement> items = getSuccessStories();
		List<String> beforeClickItems = new ArrayList<String>();
		String name;
		for(WebElement item : items){
			name = item.getText();
			System.out.println("Adding: " + name);
			beforeClickItems.add(name);
		}
	}

}
