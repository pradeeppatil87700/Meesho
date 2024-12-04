package tests;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

import pages.HomePage;
import utils.BrowserUtils;

public class HomeTest extends BasePage {
	private WebDriver driver;
	private HomePage homePage;
	private ExtentTest test;

	@BeforeClass
	public void setup() {
		driver = getDriver();
		homePage = new HomePage(driver);
		test = extent.createTest("Home Functionality Check");
	}

	
	
	
	
	
	
	
	@Test
	public void verifyHomePage() {
	    try {
	        homePage.openHomePage("https://www.meesho.com/", test);

	        // Verify Title
	        String fetchedTitle = homePage.getHomePageTitle(test);
	        Assert.assertTrue(fetchedTitle.contains("Meesho"), "Title does not match expected value!");

	        // Log success
	        test.pass("Homepage title verified successfully.");
	    } catch (AssertionError e) {  //3
	        String screenshotPath = BrowserUtils.captureScreenshot(driver, "verifyHomePage_Failure");
	        test.fail("Homepage title verification failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
	        throw e; // Re-throw to maintain failure status
	    }
	}

	 
	  
	 
	
	
	
	
	
	

	@Test
	public void verifyNavigationToBags() {
		try {
			homePage.openHomePage("https://www.meesho.com/", test);
			homePage.hoverOverMenCategory(test);
			homePage.clickBags(test);
			Assert.assertTrue(driver.getTitle().contains("Bags"), "Page title does not contain 'Bags'.");
			test.pass("Navigation to 'Bags' verified successfully.");
		} catch (AssertionError e) {
			String screenshotPath = BrowserUtils.captureScreenshot(driver, "verifyHomePage_Failure");
			test.fail("Navigation to 'Bags' verification failed: " + e.getMessage())
					.addScreenCaptureFromPath(screenshotPath);
			throw e;
		}
	}

	@AfterClass
	public void tearDown() {
		super.tearDown();
	}
}