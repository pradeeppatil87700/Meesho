package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;
import utils.ExtentReportListener;

public class BasePage extends ExtentReportListener {
    protected WebDriver driver;

    // Initialize WebDriver based on config.properties
    public void initializeDriver() {
        try {
            String browser = ConfigReader.getProperty("browser");
            String driverPath;

            // Check browser type and set driver path
            if ("chrome".equalsIgnoreCase(browser)) {
                driverPath = ConfigReader.getProperty("chromeDriverPath");
                if (driverPath == null || driverPath.isEmpty()) {
                    throw new IllegalArgumentException("Chrome driver path is not specified in the configuration.");
                }
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();

            } else if ("firefox".equalsIgnoreCase(browser)) {
                driverPath = ConfigReader.getProperty("firefoxDriverPath");
                if (driverPath == null || driverPath.isEmpty()) {
                    throw new IllegalArgumentException("Firefox driver path is not specified in the configuration.");
                }
                System.setProperty("webdriver.gecko.driver", driverPath);
                driver = new FirefoxDriver();

            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Maximize the browser window
            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing the WebDriver: " + e.getMessage(), e);
        }
    }

    // Getter for WebDriver
    public WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    // Quit driver
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
