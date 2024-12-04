package tests;

import base.BasePage;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RedirectionCheckPage;
import utils.BrowserUtils;

import com.aventstack.extentreports.ExtentTest;

public class RedirectionCheck extends BasePage {
	private WebDriver driver;
	private RedirectionCheckPage redirectionCheckPage;
	private ExtentTest test;

	@BeforeClass
	public void setup() {
		driver = getDriver();
		redirectionCheckPage = new RedirectionCheckPage(driver);
		// Initialize ExtentTest
		test = extent.createTest("Major Button Page Redirection Check");
	}

	@Test
	public void downloadMeesho() {
		try {
			// Step 1: Open the Meesho website
			redirectionCheckPage.openPage("https://www.meesho.com/", test);

			// Step 2: Click the "Download the Meesho App" button
			redirectionCheckPage.clickDownloadButton(test);

			// Step 3: Print success message
			System.out.println("Successfully clicked the 'Download the Meesho App' button!");
			test.pass("Successfully clicked the 'Download the Meesho App' button.");
		} catch (Exception e) {
			test.fail("Test failed: " + e.getMessage());
		}
	}

	@Test
	public void clickProfileTest() {
		try {
			// Step 1: Open the Meesho website
			redirectionCheckPage.openPage("https://www.meesho.com/", test);

			// Step 2: Click the "Profile" button and capture the extracted text
			String extractedText = redirectionCheckPage.clickProfile(test);

			// Step 3: Print the extracted text
			System.out.println("Extracted text from Profile dropdown: " + extractedText);

			// Step 4: Add assertion to verify that the extracted text matches the expected
			// value
			String expectedText = "Hello User";
			Assert.assertEquals(extractedText, expectedText, "The extracted text does not match the expected text!");

			// Step 5: Print a success message
			System.out.println("Profile dropdown is opened successfully.");
			test.pass("Profile dropdown opened successfully.");
		} catch (Exception e) {
			String screenshotPath = BrowserUtils.captureScreenshot(driver, "verifyHomePage_Failure");
			test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
		}
	}

	@Test
	public void clickCartTest() {
		try {
			// Step 1: Open the Meesho website
			redirectionCheckPage.openPage("https://www.meesho.com/", test);

			// Step 2: Click the "Cart" button and capture if it was clickable
			boolean isCartOpened = redirectionCheckPage.clickCart(test);

			// Step 3: Validate and print success or failure
			if (isCartOpened) {
				System.out.println("Cart successfully opened.");
				test.pass("Cart opened successfully.");
			} else {
				System.out.println("Cart button was not clickable.");
				test.fail("Cart button was not clickable.");
			}
		} catch (Exception e) {
			String screenshotPath = BrowserUtils.captureScreenshot(driver, "verifyHomePage_Failure");
			test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
		}
	}

	@Test
	public void signUpTest() throws Exception {
		try {
			// Step 1: Open the Meesho website
			redirectionCheckPage.openPage("https://www.meesho.com/", test);

			// Step 2: Perform the Sign-Up flow and retrieve supplier and seller texts
			redirectionCheckPage.clickSignUp(test);
			// Step 6: Log success
			test.pass("Sign-up test completed successfully.");
		} catch (Exception e) {
			String screenshotPath = BrowserUtils.captureScreenshot(driver, "verifyHomePage_Failure");
			test.fail("Sign-up test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
			throw e;
		}
	}

	@AfterClass
	public void tearDown() {
		// Flush the report after the tests are done
		super.tearDown();
	}
}
