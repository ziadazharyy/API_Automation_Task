package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestNGExtentReport implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports and attach reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a test node for each test method
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable()); // Log exception
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();  // Write everything to report
    }

    // Optional overrides:
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
}
