package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class BrowserUtils {
	
	
	// provide  the captured path to the report of the screenshot 2
	public static String captureScreenshot(WebDriver driver, String stepName) {
	    try {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
	        String screenshotName = stepName + "_" + timestamp + ".png";
	        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + screenshotName;
	        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/target/screenshots")); // Ensure the directory exists
	        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
	        System.out.println("Screenshot captured: " + screenshotPath);
	        return screenshotPath; // Return the full path
	    } catch (IOException e) {
	        System.err.println("Failed to capture screenshot. Error: " + e.getMessage());
	        return null; // Return null if capture fails
	    }
	}

	
	
	
	
	
	
	

	
	
	
	 // Extract text from a visible element
    public static String getElementText(WebDriver driver, By locator, Duration timeout) {
        WebElement element = waitForVisibility(driver, locator, timeout);
        return element.getText();
    }
    
    
    
    
    
    // Click an element with clickability wait
    public static void clickWhenClickable(WebDriver driver, By locator, Duration timeout) {
        WebElement element = waitForClickability(driver, locator, timeout);
        element.click();
    }
    
    
    
    
    
    
 // Switch to a newly opened tab
    public static void switchToNewTab(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.hasNext() ? iterator.next() : null;
        if (childWindow != null) {
            driver.switchTo().window(childWindow);
        } else {
            throw new NoSuchWindowException("New tab was not opened.");
        }
    }
    
    
    // Switch back to the parent tab
    public static void switchToParentTab(WebDriver driver, String parentWindow) {
        driver.switchTo().window(parentWindow);
    }
    
    
    
    
    
    
    
    
	
	
	

    // Wait for an element to be visible
    public static WebElement waitForVisibility(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for an element to be clickable
    public static WebElement waitForClickability(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    

    // Wait for title to contain a specific text
    public static void waitForTitleContains(WebDriver driver, String titleFragment, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.titleContains(titleFragment));
    }

    // Hover over an element
    public static void hover(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Get page title
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // Click an element after waiting for it to be clickable
    public static void clickElement(WebDriver driver, By locator, Duration timeout) {
        WebElement element = waitForClickability(driver, locator, timeout);
        element.click();
    }
}
