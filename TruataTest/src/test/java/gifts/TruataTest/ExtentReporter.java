package gifts.TruataTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/***
 * 
 * Class that generates reports when test is executed
 *
 */

public class ExtentReporter {

	static ExtentReports extent;

	// Returns Extent Reports Object

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("QA Automation Challenge");

		// Calling Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shruthy");
		return extent;

	}

}
