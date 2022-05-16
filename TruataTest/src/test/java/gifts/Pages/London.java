package gifts.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**********
 * 
 * Class that captures the operations London Spa Days pge
 *
 */
public class London extends gifts.TruataTest.Base {

	// Function that verifies if user is redirected to the correct page

	public void comparePageName(String value) {

		// Checking page name displayed
		String pageName = driver.findElement(By.cssSelector("div.col-sm-12 h1")).getText();

		if (pageName.equalsIgnoreCase(value))
			System.out.println("User is correctly redirected to page: " + pageName);

		else {

			System.out.println("User not redirected to the right page");
			Assert.assertTrue(false);
		}
	}

	// Function that returns all the filter values above Find a location

	public ArrayList<String> getFilteredItems() {

		List<WebElement> filter_elements = driver.findElements(By.cssSelector("span.bcr-text"));
		ArrayList<String> filter_values = new ArrayList<String>();

		for (WebElement i : filter_elements) {

			filter_values.add(i.getText());
		}

		return filter_values;
	}

	// Comparing if sub menu selection done in Home page is sames as listed filter
	// values above FIND A LOCATION

	public void compMenuandFilter(ArrayList<String> menu, ArrayList<String> filter) {

		for (String s : filter) {

			if (menu.contains(s)) {

				System.out.println("Menu " + s + " item is present in the filter products");
			}

			else {
				System.out.println("Menu " + s + " item is NOT present in the filter products");
			}
		}
	}

	// Returns the name of the checkbox that are enabled in London Spa Days page

	public ArrayList<String> getCheckboxName() {

		ArrayList<String> filter_by = new ArrayList<String>();

		List<WebElement> filter_label = driver.findElements(By.xpath("//input[@type = 'checkbox']/parent::label"));
		List<WebElement> filter_checkbox = driver
				.findElements(By.xpath("//input[@type = 'checkbox']/parent::label/input"));
		for (int i = 0; i < filter_checkbox.size(); i++) {

			if (filter_checkbox.get(i).isSelected()) {
				filter_by.add(filter_label.get(i).getText());
				System.out.println(filter_label.get(i).getText() + " is checked");
			}

		}
		return filter_by;
	}

}
