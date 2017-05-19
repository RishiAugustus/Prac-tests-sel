package test.java.BlogTests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.pageobjects.BlogPage;
import main.java.pageobjects.SchneiderHomePage;
import test.java.Basic.SuiteBaseTemplate;

public class BlogTests extends SuiteBaseTemplate {

	@BeforeClass
	public void init(){
		
		PageFactory.initElements(driver, SchneiderHomePage.class);
		PageFactory.initElements(driver, BlogPage.class);
	}
	
	@Test(groups="Blog-Tests", priority=1)
	public void verifyBlogPage(){
		SchneiderHomePage.goToBlogPage();
		Assert.assertEquals(BlogPage.getBlogPageTitle(), Param.getProperty("BlogTitle"));
	}
	
	@Test(groups="Blog-Tests", priority=2)
	public void verifySearchResults(){
		String searchString = "DYNSIM";
		BlogPage.searchBlog(searchString);
		System.out.println(BlogPage.getSearchMessage());
		String messageString = BlogPage.getSearchMessage();
		Assert.assertEquals(true,messageString.toLowerCase().contains(searchString.toLowerCase()));
		
	}
	
	@Test(groups="Blog-Tests", priority=3)
	public void checkDateSort(){
		BlogPage.sortBlogByDate();
		Assert.assertTrue(BlogPage.verifySortedDates());
	}
	
	@Test(groups="Blog-Tests", priority=4)
	public void verifyDateSortLinkChangeToRelevance(){
		Assert.assertFalse(BlogPage.verifySortByDateChangeToSortByRelevance());
	}
	
	@Test(groups="Blog-Tests", priority=5)
	public void verifyBlogArchive(){
		Assert.assertTrue(BlogPage.verifyBlogArchive());
	}
	
	@Test(groups="Blog-Test", priority=6)
	public void verifyAuthorLinkedProfile(){
		Assert.assertTrue(BlogPage.verifyAuthorLinkedinProfile());
	}

}
