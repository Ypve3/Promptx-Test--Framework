package Chat_Response;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class Chat_Response extends BaseClass {

	
	@Test()
    public void ChatResponse() throws InterruptedException {
//        test = reports.createTest("Chat_Response"); // Start ExtentReports logging
        driver.findElement(By.xpath("//div[@title='Chat Search']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Ask something to AI']")).sendKeys("Promptx" + Keys.RETURN);
        Thread.sleep(20000);
        driver.quit();
      
    }
	
	
}


