package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ExtentReportListener {

    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeSuite
    public void setupExtentReports() {
        String reportPath = System.getProperty("user.dir") + "/target/extent-report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Pradeep Patil");
        extent.setSystemInfo("Environment", "QA");
    }

    public synchronized void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThread.set(test);
    }

    public synchronized ExtentTest getTest() {
        return testThread.get();
    }
    
    //screenshot in report 1
    public synchronized void addScreenshotToReport(String screenshotPath) {
        if (screenshotPath != null) {
            getTest().addScreenCaptureFromPath(screenshotPath);
        }
    }

    
    
    
    
    

    @AfterSuite
    public void teardownExtentReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
