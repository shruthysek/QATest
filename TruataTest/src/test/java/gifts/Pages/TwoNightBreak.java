package gifts.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * 
 * Search Page Two Night Break, selected from Home page
 *
 */

public class TwoNightBreak extends gifts.TruataTest.Base {

	// Verifies page name

	public void checkPageName() {

		String pageName = driver.findElement(By.xpath("//div[@class='col-sm-12']//h1")).getText();
		Assert.assertEquals(pageName, "Two Night Breaks");
		System.out.println("Page Name validated and is: " + pageName);
	}

	// Check first item is marked as No:1 BestSeller

	public void checkBestSeller(String seller) {

		String checkBestSeller = driver.findElement(By.xpath("//div[@class='productsash']//span")).getText();

		if (checkBestSeller.equalsIgnoreCase(seller)) {

			System.out.println("The first item is best seller");

		}

		else {

			System.out.println("The first item is not best seller");
			Assert.assertTrue(false);

		}

	}

	public void checkHotelName(String hotel) {

		String name = driver.findElement(By.xpath("//div[@class='productsash']/following-sibling::div/h3")).getText();

		if (name.contains(hotel)) {
			System.out.println("Name is: " + name);
		}

		else {
			System.out.println("Populated " + name + "not expected");
			Assert.assertTrue(false);
		}
	}

	public void checkPrice(String actualprice) {

		String amount = driver.findElement(By.cssSelector("div.currentprice")).getText();
		String[] price = amount.split("£");
		if (price[1].equalsIgnoreCase(actualprice)) {

			System.out.println("The price value as expected is £" + price[1]);
		}

		else {

			System.out.println("The price value is not as expected and is £" + price[1]);

			Assert.assertTrue(false);
		}
	}
}
