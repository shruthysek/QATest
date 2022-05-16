package gifts.Pages;

import org.openqa.selenium.By;

/************
 * 
 * Class that consist of all the operations performed in Basket Page
 *
 */

public class Basket extends gifts.TruataTest.Base {

	// Function to get the Standard delivery fees for an item in the basket

	public float getStdFees() {

		String fees = driver.findElement(By.cssSelector("select#basketDeliverySelect option")).getText();
		System.out.println("Delivery Fees is: " + fees);

		// Separating the amount from the string

		String[] fees_split1 = fees.split("£");
		fees_split1[1] = fees_split1[1].replace(")", "");
		float delv_fees = Float.parseFloat(fees_split1[1]);
		return delv_fees;
	}

	// Function to return the total from the Basket Summary

	public float getTotal() {

		String total = driver.findElement(By.xpath("//div[@class='row final_totals']//span[2]")).getText();

		// Remove euro symbol and convert to float
		String grand_total = total.replace("£", "");

		// Convert string to float
		return Float.parseFloat(grand_total);
	}

}
