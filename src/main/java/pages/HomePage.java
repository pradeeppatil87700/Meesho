package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;
import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	private By menCategory = By
			.xpath("(//div[@class='NavBarDesktop__Subtitle-sc-xsryom-0 izctWK']/span[text()='Men'])[1]");
	private By bagsLink = By.xpath("//a[contains(@href, '/bags-backpacks-men/pl/3kr')]/p[text()='Bags']");

	public void openHomePage(String url, ExtentTest test) {
		try {
			driver.get(url);
			test.info("Navigated to URL: " + url);
		} catch (Exception e) {
			test.fail("Failed to navigate to URL: " + url + ". Error: " + e.getMessage());
			throw e;
		}
	}

	public String getHomePageTitle(ExtentTest test) {
		try {
			String title = BrowserUtils.getPageTitle(driver);
			test.info("Fetched page title: " + title);
			return title;
		} catch (Exception e) {
			test.fail("Failed to fetch page title. Error: " + e.getMessage());
			throw e;
		}
	}

	public void hoverOverMenCategory(ExtentTest test) {
		try {
			BrowserUtils.hover(driver, driver.findElement(menCategory));
			test.info("Hovered over the 'Men' category successfully.");
		} catch (Exception e) {
			test.fail("Failed to hover over the 'Men' category. Error: " + e.getMessage());
			throw e;
		}
	}

	public void clickBags(ExtentTest test) {
		try {
			BrowserUtils.clickElement(driver, bagsLink, Duration.ofSeconds(10));
			test.info("Clicked on the 'Bags' link.");
			BrowserUtils.waitForTitleContains(driver, "Bags", Duration.ofSeconds(10));
			String pageTitle = driver.getTitle();
			test.info("Page title after clicking 'Bags': " + pageTitle);
		} catch (Exception e) {
			test.fail("Failed to click on 'Bags' or verify title. Error: " + e.getMessage());
			throw e;
		}
	}
}
