package InternetSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class InternetSearch extends BaseClass{

	
	
	@Test
	public void InterNetSearch() throws InterruptedException {
		 test = reports.createTest("Valid Scenario Test"); // Start ExtentReports logging
	        driver.findElement(By.xpath("//div[@title='Internet Search']")).click();
	        driver.findElement(By.xpath("//input[@placeholder='Ask something to AI']")).sendKeys("US Election Results" + Keys.RETURN);
	        Thread.sleep(50000);

	}
}
