package Title_Manipulation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Chat_4Delete extends BaseClass {

    private WebDriverWait wait;

    @Test
    public void Delete() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click on the ellipsis icon
            WebElement ellipsisIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@aria-label='ellipsis'])[1]")));
            ellipsisIcon.click();

            // Wait for the 'Delete Chat' option to be visible and click it
            WebElement deleteChatOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Delete Chat']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteChatOption);
            deleteChatOption.click();

            // Wait for the 'Delete' confirmation button and click it
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Delete']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
            deleteButton.click();

            // Wait for the notification message
            WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
            ));
            wait.until(ExpectedConditions.textToBePresentInElement(notification, "Chat Deleted Successfully"));

            // Validate the notification message
            String actualMessage = notification.getText();
            System.out.println("Actual Notification Message: " + actualMessage);

            String expectedMessage = "Chat Deleted Successfully";
            Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            Assert.fail("Unexpected exception: " + e.getMessage());
        } 
        
    }
}
