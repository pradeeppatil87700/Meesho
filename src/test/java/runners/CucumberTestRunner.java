package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ExtentReportListener;

@CucumberOptions(
        features = "src/test/resources/features", // Location of your feature files
        glue = {"steps"}, // Package containing step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}, // Added JSON report for better integration with CI tools
        monochrome = true // Better readability of console output
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        ExtentReportListener extentReportListener = new ExtentReportListener();
        extentReportListener.setupExtentReports(); // Initialize Extent Reports
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ExtentReportListener extentReportListener = new ExtentReportListener();
        extentReportListener.teardownExtentReports(); // Finalize Extent Reports
    }
}
