package gifts.TruataTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/*****
 * 
 * Listens to events in the test scripts and behaves accordingly
 *
 */

public class Listeners extends Base implements ITestListener {

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	ExtentReports extent = ExtentReporter.getReportObject();
	ExtentTest test;

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***************** Successfully executed " + result.getMethod().getMethodName() + "*************");
		extentTest.get().generateLog(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().fail(result.getThrowable());
		// Screenshot
		String testMethodName = result.getMethod().getMethodName();
		String destination = null;

		try {

			destination = getScreenShotPath(testMethodName, driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			extentTest.get().addScreenCaptureFromPath(destination, testMethodName);

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("********** Test Failed for " + testMethodName + "***************");
		extentTest.get().log(Status.FAIL, "Test Failed");

	}

	/*
	 * public void onTestSkipped(ITestResult result) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * public void onTestFailedButWithinSuccessPercentage(ITestResult result) { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * public void onTestFailedWithTimeout(ITestResult result) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * public void onStart(ITestContext context) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 */

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
