package framework;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IInvokedMethodListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestNgListners implements ITestListener{
	 private ExtentReports extent;
	    private ExtentTest test;

	    @Override
	    public void onStart(ITestContext context) {
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./TestNGExtent_Report/ExtentReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        String className = result.getTestClass().getName();
	        test = extent.createTest(className);
	        System.out.println("Currently running class: " + className);

	    }
	    
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, "Failure Reason: " + result.getThrowable().getMessage());
	        System.out.println("Test Failed: " + result.getMethod().getMethodName());
	        System.out.println("Failure Reason: " + result.getThrowable().getMessage());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.log(Status.SKIP, "Test Skipped");
	        test.log(Status.SKIP, "Reason for Skip: " + result.getThrowable().getMessage());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }

}
