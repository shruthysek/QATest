package gifts.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/***
 * 
 * Selected product page from Two Nights Break page
 *
 */

public class SelectedProduct extends gifts.TruataTest.Base {

	// Verifies product name

	public void getProductName(String expectedProd) {

		// Get product name displayed in the selected page

		String productName = driver.findElement(By.cssSelector("span.product-name")).getText();
		Assert.assertEquals(productName, expectedProd);
		System.out.println("Product name obtained from the selected page is: " + productName);
	}

	// Verifies product price

	public void getProductPrice(String expectedPrice) {

		String currentPrice = driver.findElement(By.cssSelector("div#product-price-current")).getText();
		Assert.assertEquals(expectedPrice, currentPrice);
		System.out.println("Price displayed in the selected page is: " + currentPrice);
	}

	// Clicks on Buy Now from the Selected Product page

	public void clickBuyNow() throws InterruptedException {

		WebElement priceDrop = driver.findElement(By.cssSelector("div.price-drop"));
		WebElement buyNow = driver.findElement(By.cssSelector("div.product-buynow button"));
		performScroll(priceDrop);
	    Thread.sleep(4000);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(buyNow));
		buyNow.click();
		System.out.println("Buy Now is selected");
	}

}
