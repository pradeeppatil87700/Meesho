package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import pages.HomePage;
import base.BasePage;

public class HomeTestSteps extends BasePage {
    private WebDriver driver;
    private HomePage homePage;
    private String pageTitle;
    private ExtentTest test;

    // Constructor
    public HomeTestSteps() {
        this.driver = getDriver();
        this.homePage = new HomePage(driver);
    }

    // Initialize the ExtentTest for each scenario
    public void startTest(String testName, String description) {
        this.test = extent.createTest(testName, description);
    }

    @Given("I navigate to the Meesho home page")
    public void navigateToHomePage() {
        startTest("Navigate to Meesho Home Page", "Navigate to the Meesho home page");
        try {
            homePage.openHomePage("https://www.meesho.com/", test);
            test.info("Navigated to Meesho home page.");
        } catch (Exception e) {
            test.fail("Failed to navigate to Meesho home page: " + e.getMessage());
        }
    }

    @When("I fetch the page title")
    public void fetchPageTitle() {
        try {
            pageTitle = homePage.getHomePageTitle(test);
            System.out.println("Fetched Title: " + pageTitle);
            test.info("Fetched the page title: " + pageTitle);
        } catch (Exception e) {
            test.fail("Failed to fetch the page title: " + e.getMessage());
        }
    }

    @Then("the title should contain {string}")
    public void verifyTitle(String expected) {
        try {
            Assert.assertTrue(pageTitle.contains(expected), "Title does not match expected value!");
            test.pass("Title contains: " + expected);
        } catch (AssertionError e) {
            test.fail("Title verification failed: " + e.getMessage());
            throw e;
        }
    }

    @When("I hover over the {string} category")
    public void hoverOverCategory(String category) {
        try {
            if (category.equalsIgnoreCase("Men")) {
                homePage.hoverOverMenCategory(test);
                test.info("Hovered over the 'Men' category.");
            } else {
                throw new IllegalArgumentException("Unsupported category: " + category);
            }
        } catch (Exception e) {
            test.fail("Failed to hover over the category '" + category + "': " + e.getMessage());
            throw e;
        }
    }

    @When("I click the {string} link")
    public void clickLink(String link) {
        try {
            if (link.equalsIgnoreCase("Bags")) {
                homePage.clickBags(test);
                test.info("Clicked on the 'Bags' link.");
            } else {
                throw new IllegalArgumentException("Unsupported link: " + link);
            }
        } catch (Exception e) {
            test.fail("Failed to click the link '" + link + "': " + e.getMessage());
            throw e;
        }
    }

    @Then("the page title should contain {string}")
    public void verifyPageTitle(String expected) {
        try {
            String currentTitle = driver.getTitle();
            System.out.println("Current Title: " + currentTitle);
            Assert.assertTrue(currentTitle.contains(expected), "Page title does not contain '" + expected + "'");
            test.pass("Page title contains: " + expected);
        } catch (AssertionError e) {
            test.fail("Page title verification failed: " + e.getMessage());
            throw e;
        }
    }
}