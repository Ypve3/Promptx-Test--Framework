package Title_Manipulation;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Chat_3Public extends BaseClass {
   
	private WebDriverWait wait;
	
	@Test(priority = 1)
	public void publicChat() {
		try {
		 	
			driver.findElement(By.xpath("(//span[@aria-label='ellipsis'])[1]")).click();
	     driver.findElement(By.xpath("//span[text()='Public Chat']")).click();
	     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
	            ));
	        
	        wait.until(ExpectedConditions.textToBePresentInElement(notification, "Successfully Moved to Public Chats"));

	        String actualMessage = notification.getText();
	        System.out.println("Actual Notification Message: " + actualMessage);

	        String expectedMessage = "Successfully Moved to Public Chats";

	        // Assert the notification text
	        Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");
	       
	        driver.findElement(By.xpath("//div[normalize-space()='Public']")).click();
    		driver.findElement(By.xpath("(//span[@class='anticon anticon-more ant-dropdown-trigger'])[1]")).click();
    		driver.findElement(By.xpath("//span[text()='Make Chat Private']")).click();

	    } catch (Exception e) {
	        System.out.println("Test failed: " + e.getMessage());
	        Assert.fail("Unexpected exception: " + e.getMessage());
	    }
		}
            
            
    		

	}

		
		
