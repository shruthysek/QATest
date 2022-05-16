package gifts.TruataTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

/**
 * 
 * Class that includes common operations performed by all tests
 *
 */

public class Base {

	public static WebDriver driver;
	Properties prop;

	// Functions that returns values from the properties file

	public String getValue(String key) throws IOException {
		Properties prop = new Properties();
		prop = new Properties();
		FileInputStream fs = new FileInputStream("E:\\Selenium\\TruataTest\\src\\test\\java\\data.properties");
		prop.load(fs);
		String value = prop.getProperty(key);
		return value;
	}

	// Initializing the driver object

	public void initialize() throws IOException {

		// Get browser details from properties file

		String browser = getValue("browser");

		// Call browser based on returned browser type
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_101\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else {

			System.setProperty("webdriver.gecko.driver", "E:\\geckodriver_v31\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

	}

	// Function that calls the landing page based on URL present in properties file

	public void getPage() throws InterruptedException, IOException {
		driver.get(getValue("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Accepting cookies
		WebElement allowall = driver.findElement(By.xpath("//div[@class='banner-actions-container']//button"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(allowall));
		allowall.click();
		// Thread.sleep(4000);

	}

	// Function that performs scroll operation

	public static void performScroll(WebElement locator) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", locator);
	}

	// Function that generates screenshot while executing tests

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\results\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
}
