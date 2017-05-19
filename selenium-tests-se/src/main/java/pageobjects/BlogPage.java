package main.java.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;


public class BlogPage extends BasePageClass {

	@FindBy(css="input.gsc-input")
	private static WebElement txt_searchBlog;
	
	@FindBy(css=".gsc-search-button")
	private static WebElement btn_searchBlog;
	
	@FindBy(css="div.status-msg-body")
	private static WebElement msgString;
	
	@FindBy(linkText="Sort by date")
	private static WebElement lnk_sortByDate;
	
	@FindAll(@FindBy(css=".date-header>span"))
	private static List<WebElement> label_date;
	
	@FindBy(css="div.status-msg-body>a:first-of-type")
	private static WebElement lnk_sortByRelevance;
	
	@FindBy(css=".widget-content>div>a>img[src*='linkedin']")
	private static WebElement img_Linkedin;
	
	@FindBy(css="div.widget-content>b>a")
	private static WebElement lnk_AuthorName;
	
	private static String sort_by_date;
	
	public BlogPage(WebDriver driver) {
		super(driver);
		//PageFactory.initElements(driver, BlogPage.class);
	}
	
	public static String getBlogPageTitle(){
		return driver.getTitle();
	}
	
	public static void searchBlog(String searchString){
		try{
			txt_searchBlog.sendKeys(searchString);
			btn_searchBlog.click();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getSearchMessage(){
		try{
			 
			return msgString.getText();
			 
		} catch(Exception e){
			
			return "";
		}
		
	}
	
	public static List<WebElement> getBlogDate(){
		return label_date;
	}
	
	public static void sortBlogByDate(){
		BlogPage.sort_by_date = lnk_sortByDate.getText();
		lnk_sortByDate.click();
	}
	
	@SuppressWarnings("deprecation")
	public static boolean verifySortedDates(){
		List<WebElement> elementList = getBlogDate();
		ArrayList<Date> obtainedList = new ArrayList<>();
		
		for(WebElement elem : elementList){
			String dateString = elem.getText();
			String sanDayString = dateString.substring(dateString.indexOf(",") + 1).trim();
			obtainedList.add(new Date(sanDayString));
		}
		//copy to new list
		ArrayList<Date> sortedList = new ArrayList<>();
		for(Date s : obtainedList){
			sortedList.add(s);
			//System.out.println("Added in sortedList: " + s);
		}
		
		/*The reverse(list) method doesn’t sort the list, 
		it just re-order the list’s elements in the reverse order. 
		Therefore the list must be sorted using the sort(list) 
		method before being reversed in order to have the list sorted in descending order.	*/
		
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return sortedList.equals(obtainedList);
	}
	
	public static boolean verifySortByDateChangeToSortByRelevance(){
		
		return sort_by_date.equals(lnk_sortByRelevance.getText().toString());
		
	}
	
	public static boolean verifyBlogArchive(){
		//assuming that archives are present
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement elem = driver.findElement(By.xpath(".//*[@id='BlogArchive1_ArchiveList']/ul[3]/li/a[2]"));
		String archiveYear = elem.getText();
		//System.out.println(elem.getText());
		BasePageClass.moveToElementJs(elem);
		elem.click();
		String CurrentURL = driver.getCurrentUrl();
		return CurrentURL.toLowerCase().contains(archiveYear.toLowerCase());

	}
	
	public static boolean verifyAuthorLinkedinProfile(){
		String AuthorName = lnk_AuthorName.getText().replaceAll("\\s+", "");
		//System.out.println("Author:" + AuthorName);
		img_Linkedin.click();
		String CurrentUrl = driver.getCurrentUrl();
		//System.out.println(CurrentUrl);
		return CurrentUrl.toLowerCase().contains(AuthorName.toLowerCase());
	}
	
}
