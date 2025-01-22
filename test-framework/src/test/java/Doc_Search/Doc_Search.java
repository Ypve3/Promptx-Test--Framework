package Doc_Search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Doc_Search extends BaseClass{

	
	@Test()
    public void Doc_Search() throws InterruptedException {
         // Start ExtentReports logging
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@title='Files Search']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Ask something to AI']")).sendKeys("Dbs" + Keys.RETURN);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='row-doc-details']"));

        // Loop through the elements and print the text
        boolean containsDBS = false;
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.contains("DBS")) {
                containsDBS = true;
                System.out.println("Text containing 'DBS' found: " + text);
            }
        }

        // Assertion
        if (containsDBS) {
            System.out.println("Assertion Passed: At least one element contains 'DBS'.");
        } else {
            System.out.println("Assertion Failed: No element contains 'DBS'.");
        }
       	
        driver.quit();
        
	
}
}
