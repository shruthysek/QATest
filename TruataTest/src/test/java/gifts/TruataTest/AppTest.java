package gifts.TruataTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import dev.failsafe.Timeout;

/**
 * 
 * Class that performs all the tests
 *
 */

public class AppTest extends Base

{

	@BeforeMethod
	public void getDriver() throws InterruptedException, IOException {
		Base obj = new Base();
		obj.initialize();
		System.out.println("Browser is initialised");

	}

	// Test that executes the first requirement of QA Automation Challenge

	@Test
	public void FirstQuestion() throws InterruptedException, IOException {

		gifts.Pages.Home hobj = new gifts.Pages.Home();
		gifts.Pages.TwoNightBreak tobj = new gifts.Pages.TwoNightBreak();
		Base baseobj = new Base();

		// Call base page
		baseobj.getPage();

		// Search for Two Night Hotel Break
		hobj.searchItem("Two Night Hotel Break"); 

		// Check Page Name
		tobj.checkPageName();

		// Perform Scroll Operation to get the first item
		WebElement location = driver.findElement(By.id("productlist-locationsearch-text"));
		performScroll(location);

		String[] checkDetails = { "BestSeller", "Two Night Hotel Break", "99" };

		// Check first item is marked as No:1 BestSeller
		tobj.checkBestSeller(checkDetails[0]);

		// Check the name of the hotel

		tobj.checkHotelName(checkDetails[1]);

		// Check the price of No:1 Best Seller
		tobj.checkPrice(checkDetails[2]);

	}

	// Test that executes the second requirement of QA Automation Challenge

	@Test
	public void SecondQuestion() throws InterruptedException, IOException {

		gifts.Pages.Home hobj = new gifts.Pages.Home();
		gifts.Pages.TwoNightBreak tobj = new gifts.Pages.TwoNightBreak();
		gifts.Pages.SelectedProduct sobj = new gifts.Pages.SelectedProduct();
		gifts.Pages.Basket bobj = new gifts.Pages.Basket();
		Base baseobj = new Base();

		// Call base page

		baseobj.getPage();
		// driver.navigate().refresh();
		// Search for Two Night Hotel Break

		hobj.searchItem("Two Night Hotel Break");

		// Scroll to first item returned
		WebElement location = driver.findElement(By.id("productlist-locationsearch-text"));
		performScroll(location);

		// Get name and price from results page
		String product_title = driver.findElement(By.cssSelector("h3.producttitle")).getText();
		String product_price = driver.findElement(By.cssSelector("div.currentprice")).getText();

		// Open product page for the bestseller
		WebElement firstProduct = driver.findElement(By.xpath("//div[@class = 'productlist-details']"));
		firstProduct.click();

		// Verify Product Name displayed in the selected page
		sobj.getProductName(product_title);

		// Verify Product price displayed in the selected page
		sobj.getProductPrice(product_price);

		// Click on Buy Now		
		sobj.clickBuyNow();		

		// Calculate total amount based on std delivery fees and product price in the
		// basket
		float delv = bobj.getStdFees();
		float price = Float.parseFloat(product_price.replace("£", ""));
		float pay_amount = price + delv;

		// Scroll to Basket Summary
		WebElement basket = driver.findElement(By.id("basket_summary_section"));
		performScroll(basket);

		// Check if amount in total is correct
		float req_total = bobj.getTotal();
		Assert.assertEquals(req_total, pay_amount);
		System.out.println("The total in the Basket page is correct: £" + req_total);

		// Go to checkout page
		WebElement paySecurely = driver.findElement(By.xpath("//div[@id='basket_payment_options']//button"));
		paySecurely.click();

		// Verify Product Name in checkout page
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement checkout = driver.findElement(By.cssSelector("div.prd_info span"));
		wait.until(ExpectedConditions.visibilityOf(checkout));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.prd_info span")));
		String product_name_checkout = checkout.getText();		
		Assert.assertEquals(product_title, product_name_checkout);

		// Verify Product price in Checkout Page Summary and Basket Item total
		String price_checkout = driver.findElement(By.xpath("//div[@class = 'prd_info']//span[2]")).getText();
		String basket_amount = driver.findElement(By.xpath("//div[@class = 'row basket_totals']//span[2]")).getText();

		// Convert product price to float value
		float basketAmt = Float.parseFloat(basket_amount.replace("£", ""));
		float prod_price = Float.parseFloat(product_price.replace("£", ""));
		Assert.assertEquals(basketAmt, prod_price);
		System.out.println("Listed price is same in product page and checkout page");

		// Validate standard delivery fees in checkout page
		String del_charge = driver.findElement(By.xpath("//div[@class = 'delivery_totals']//span[2]")).getText();
		del_charge = del_charge.replace("£", "");
		float delivery_fees = Float.parseFloat(del_charge);
		if (delivery_fees == delv) {

			System.out.println(" The delivery fees in checkout page is validated");
			System.out.println("The standard delivery fees is £" + delivery_fees);
		}

		else {
			System.out.println("The delivery fees is not as expected");
			Assert.assertTrue(false);
		}

		// Get Packaging total amount
		String pack_total = driver.findElement(By.xpath("//div[@class = 'packaging_totals ng-scope']//span[2]"))
				.getText();

		// Convert string to float
		float pack_amount = Float.parseFloat(pack_total.replace("£", ""));

		// Amount displayed in check out
		String totalAmount = driver.findElement(By.xpath("//div[@class = 'row final_totals']//span[2]")).getText();
		System.out.println("Total amount below in checkout page is verified: " + totalAmount);
		float amt_checkout = Float.parseFloat(totalAmount.replace("£", ""));
		float sum = basketAmt + pack_amount + delivery_fees;
		Assert.assertEquals(sum, amt_checkout);

	}

	// Test that executes the third requirement of QA Automation Challenge

	@Test
	public void ThirdQuestion() throws InterruptedException, IOException {

		gifts.Pages.Home hobj = new gifts.Pages.Home();
		gifts.Pages.London lobj = new gifts.Pages.London();
		Base baseobj = new Base();

		// Call Base Page
		baseobj.getPage();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// Selecting London from the Menu
		hobj.selectLondon();

		// Validating the page name in London spa days page
		String value = "London Spa Days";
		lobj.comparePageName(value);

		// Check filtered items are correctly displayed in the London page
		ArrayList<String> menu_Items = new ArrayList<String>(Arrays.asList("Spa & Beauty", "Spa day", "London"));
		ArrayList<String> filter_products = lobj.getFilteredItems();
		System.out.println("Values present in filter are " + filter_products);

		// Compare menu selection and filter selection
		lobj.compMenuandFilter(menu_Items, filter_products);

		// Get all the selected checkbox and label and store the label in ArrayList
		ArrayList<String> filter_by = lobj.getCheckboxName();
		boolean flag = true;
		System.out.println("Check box selected in Filter By is: " + filter_by);

		// Validating filter values are actually checked
		for (String s : filter_products) {

			if (filter_by.contains(s)) {

				System.out.println("Filter " + s + " item is selected in Filter by area");
			}

			else {
				System.out.println("Filter  " + s + " item is NOT present in the filter by area");
				flag = false;
			}

		}

		if (flag == false)
			Assert.assertTrue(flag);
	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {

		// driver.manage().deleteAllCookies();
		driver.close();
		System.out.println("Browser is closed");

	}
}
