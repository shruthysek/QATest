package gifts.Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**********
 * 
 * Class that consist of all the operations performed in the Home Page
 *
 */

public class Home extends gifts.TruataTest.Base {

	// Search for an item in Home Page

	public void searchItem(String s) throws InterruptedException {
				
		WebElement search = driver.findElement(By.id("search-field"));
		search.sendKeys(s);
		Thread.sleep(2000);
		WebElement magnifier = driver.findElement(By.id("magnifier-search"));		
		magnifier.click();
	}

	// Selecting London from subMenu

	public void selectLondon() {

		WebElement experience = driver.findElement(By.xpath("//li[@data-text = 'Experiences']"));

		// Creating object of an Actions class

		Actions action = new Actions(driver);
		action.moveToElement(experience).perform();

		// Mouse hover on Spa & Beauty present in sub menu

		WebElement spa_beauty = driver.findElement(By.xpath("//li[@data-text = 'Spa & Beauty']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.elementToBeClickable(spa_beauty));
		System.out.println("Moved to spa element");
		action.moveToElement(experience).moveToElement(spa_beauty).build().perform();

		// Clicking on London

		WebElement london = driver.findElement(By.xpath("(//a[@href='/spa-and-beauty/spa-day/london'])[2]"));
		london.click();
	}
}
