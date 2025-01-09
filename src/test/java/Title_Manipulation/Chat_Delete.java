package Title_Manipulation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Chat_Delete extends BaseClass {
  
	private WebDriverWait wait;
	
	@Test
	public void Delete() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        try {
         test = reports.createTest("Valid Scenario Test");	
		 driver.findElement(By.xpath("//span[contains(text(),'Agile Adoption')]/following-sibling::span[@aria-label='ellipsis']")).click();
		 driver.findElement(By.xpath("//div[text()='Delete Chat']")).click();
		 driver.findElement(By.xpath("//span[text()='Delete']")).click();
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
	            ));
	        
	        wait.until(ExpectedConditions.textToBePresentInElement(notification, "Chat Deleted Successfully"));

	        String actualMessage = notification.getText();
	        System.out.println("Actual Notification Message: " + actualMessage);

	        String expectedMessage = "Chat Deleted Successfully";

	        // Assert the notification text
	        Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");
	       

	    } catch (Exception e) {
	        System.out.println("Test failed: " + e.getMessage());
	        Assert.fail("Unexpected exception: " + e.getMessage());
	    }
	}
		 
	}
