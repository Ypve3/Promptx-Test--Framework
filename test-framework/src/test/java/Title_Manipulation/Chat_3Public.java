package Title_Manipulation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Chat_3Public extends BaseClass {

    private WebDriverWait wait;

    @Test
    public void publicChat() {
        try {
            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the ellipsis icon to be clickable and click it
            WebElement ellipsisIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@aria-label='ellipsis'])[1]")));
            ellipsisIcon.click();

            // Wait for 'Public Chat' option to be visible and click it
            WebElement publicChatOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Public Chat']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", publicChatOption);

            // Wait for the notification message
            WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
            ));
            wait.until(ExpectedConditions.textToBePresentInElement(notification, "Successfully Moved to Public Chats"));

            // Validate the notification message
            String actualMessage = notification.getText();
            System.out.println("Actual Notification Message: " + actualMessage);

            String expectedMessage = "Successfully Moved to Public Chats";
            Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");

            // Navigate to the 'Public' section
            WebElement publicTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Public']")));
            publicTab.click();

            // Click the more options icon for the public chat
            WebElement moreOptionsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='anticon anticon-more ant-dropdown-trigger'])[1]")));
            moreOptionsIcon.click();

            // Select 'Make Chat Private' option
            WebElement makePrivateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Make Chat Private']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", makePrivateOption);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }
}

