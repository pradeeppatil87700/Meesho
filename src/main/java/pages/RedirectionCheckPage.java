package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.BrowserUtils;
import com.aventstack.extentreports.ExtentTest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RedirectionCheckPage {
	private WebDriver driver;

	// Constructor
	public RedirectionCheckPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for "Download the Meesho App" button
	private By downloadButton = By.xpath(
			"//div[contains(@class, 'VideoBanner__DownloadButton-sc-149azki-1') and contains(., 'Download the Meesho App')]");
	private By profileButton = By.xpath("//span[text()='Profile']");
	private By cartButton = By.xpath("//span[text()='Cart']");
	private By signUpButton = By.xpath("//div[@class='SupplierBanner__StyledButton-sc-2qsypi-2 jmVeNL']");
	private By HoverElement = By.cssSelector("div span[class='sc-eDvSVe kYLKqU']");

	// Open the page
	public void openPage(String url, ExtentTest test) {
		try {
			driver.get(url);
			test.info("Navigated to URL: " + url);
		} catch (Exception e) {
			test.fail("Failed to navigate to URL: " + url + ". Error: " + e.getMessage());
			throw e;
		}
	}

	// Click the "Download the Meesho App" button
	public void clickDownloadButton(ExtentTest test) {
		try {
			BrowserUtils.clickElement(driver, downloadButton, Duration.ofSeconds(10));
			test.info("Clicked on 'Download the Meesho App' button.");
		} catch (Exception e) {
			test.fail("Failed to click 'Download the Meesho App' button. Error: " + e.getMessage());
			throw e;
		}
	}

	public String clickProfile(ExtentTest test) {
		try {
			// Click the 'Profile' button
			BrowserUtils.clickElement(driver, profileButton, Duration.ofSeconds(10));
			test.info("Clicked on 'Profile' button.");

			// Wait for the hover element to be visible using the utility method
			BrowserUtils.waitForVisibility(driver, HoverElement, Duration.ofSeconds(10));
			test.info("Hover element is visible.");

			// Find the hover element after it is visible
			WebElement hoverElement = driver.findElement(HoverElement);

			// Extract the text from the hover element
			String extractText = hoverElement.getText();
			test.info("Extracted text from hover element: " + extractText);

			return extractText; // Return the extracted text

		} catch (Exception e) {
			test.fail("Failed to click 'Profile' button. Error: " + e.getMessage());
			throw e;
		}
	}

	public boolean clickCart(ExtentTest test) {
		try {
			// Wait for the cart button to be clickable
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(cartButton)) != null;

			if (isClickable) {
				// If the button is clickable, click it
				BrowserUtils.clickElement(driver, cartButton, Duration.ofSeconds(10));
				test.info("Clicked on 'Cart' button.");
				return true; // Return true if the button was clicked successfully
			} else {
				// If the button is not clickable
				test.fail("Cart button is not clickable.");
				return false;
			}
		} catch (Exception e) {
			test.fail("Failed to click 'Cart' button. Error: " + e.getMessage());
			throw e;
		}
	}

	private By loginButton = By.id("loginbutton");
	private By supplierTextElement = By.cssSelector("div[class='MuiBox-root css-mqxh8y'] h1");
	private By sellerTextElement = By.cssSelector(".primary-button.startselling.w-button[data-position='header']");
	private By sellerTextData = By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-hzpjot");

	public Map<String, String> clickSignUp(ExtentTest test) throws Exception {
		Map<String, String> extractedTexts = new HashMap<>(); // Store supplier and seller text

		try {
			// Step 1: Click "Sign Up" button
			BrowserUtils.clickWhenClickable(driver, signUpButton, Duration.ofSeconds(10));
			test.pass("Clicked on 'Sign Up' button.");

			// Step 2: Capture parent window handle
			String parentWindow = driver.getWindowHandle();

			// Step 3: Switch to new tab
			BrowserUtils.switchToNewTab(driver);

			// Step 4: Click "Login" button
			BrowserUtils.clickWhenClickable(driver, By.id("loginbutton"), Duration.ofSeconds(10));

			// Step 5: Extract supplier text
			String actualSupplierText = BrowserUtils.getElementText(driver, supplierTextElement,
					Duration.ofSeconds(10));
			test.info(" Supplier Login Page  : " + actualSupplierText);
			String expectedSupplierText = "Login to your supplier panel";
			Assert.assertEquals(actualSupplierText, expectedSupplierText, "Assertion failed: Supplier text mismatch!");
			test.pass("Verified : " + actualSupplierText);
			extractedTexts.put("supplierText", actualSupplierText);

			// Step 6: Navigate back and click seller text
			driver.navigate().back();
			BrowserUtils.clickWhenClickable(driver, sellerTextElement, Duration.ofSeconds(10));

			// Step 7: Extract seller text
			String actualSellerText = BrowserUtils.getElementText(driver, sellerTextData, Duration.ofSeconds(10));
			test.info("Supplier Create Account Page : " + actualSellerText);
			String expectedSellerText = "Welcome to Meesho";
			Assert.assertEquals(actualSellerText, expectedSellerText, "Assertion failed: Seller text mismatch!");
			test.pass("Verified : " + actualSellerText);
			extractedTexts.put("sellerText", actualSellerText);

			// Step 8: Navigate back, close child tab, and switch to parent tab
			driver.navigate().back();
			driver.close();
			BrowserUtils.switchToParentTab(driver, parentWindow);

		} catch (Exception e) {
			test.fail("Failed during 'Sign Up' button flow. Error: " + e.getMessage());
			throw e;
		}

		return extractedTexts; // Return both supplier and seller texts
	}

}
