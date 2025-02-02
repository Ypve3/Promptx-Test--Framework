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


public class Chat_2Bookmark extends BaseClass{

	private WebDriverWait wait;
	
	@Test(priority = 1)
    public void Bookmark() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        try {
       
        driver.findElement(By.xpath("//div[@title='Chat Search']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Ask something to AI']")).sendKeys("Agile Adoption" + Keys.RETURN);
        Thread.sleep(35000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("(//span[@aria-label='ellipsis'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Bookmark')]")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//div[contains(@class, 'ant-notification-notice')]//div[@class='ant-notification-notice-message']")
            ));
        
        wait.until(ExpectedConditions.textToBePresentInElement(notification, "Bookmarked Successfully"));

        String actualMessage = notification.getText();
        System.out.println("Actual Notification Message: " + actualMessage);

        String expectedMessage = "Bookmarked Successfully";

        
        Assert.assertEquals(actualMessage, expectedMessage, "Notification message does not match!");
       
        driver.findElement(By.xpath("//div[contains(text(),'Bookmark')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M908.1 353')]")).click();

    } catch (Exception e) {
        System.out.println("Test failed: " + e.getMessage());
        Assert.fail("Unexpected exception: " + e.getMessage());
    }
       
}}

