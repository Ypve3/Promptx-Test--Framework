package Title_Manipulation;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericLib.BaseClass;

public class Chat_1Rename extends BaseClass {
    private WebDriverWait wait; // Declare the wait variable at class level

    @Test
    public void Rename() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        try {
         
            // Initialize wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Perform actions to rename the chat
            driver.findElement(By.xpath("(//span[@aria-label='ellipsis'])[1]")).click();
            driver.findElement(By.xpath("//span[text()='Rename']")).click();

            // Clear the input field and enter new text
            driver.findElement(By.xpath("//input[@placeholder='Rename Chat Title']")).sendKeys(Keys.CONTROL + "a");
            driver.findElement(By.xpath("//input[@placeholder='Rename Chat Title']")).sendKeys(Keys.DELETE);
            driver.findElement(By.xpath("//input[@placeholder='Rename Chat Title']")).sendKeys("Automation");

            // Save changes
            driver.findElement(By.xpath("//span[text()='Save Changes']")).click();

            // Add explicit wait for notification
            WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
            ));

            // Wait for notification text to be present
            wait.until(ExpectedConditions.textToBePresentInElement(notification, "Chat rename successfully"));

            String actualMessage = notification.getText();
            System.out.println("Actual Notification Message: " + actualMessage);

            String expectedMessage = "Chat rename successfully to 'Automation'";

            // Assert the notification text
            Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");
           

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
        
        finally {
            // Ensure the browser is closed
            if (driver != null) {
                driver.quit();  // Close all windows and end the session
            }
        
    }
}}