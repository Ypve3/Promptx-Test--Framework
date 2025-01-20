package Recently_Viewed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.BaseClass;

public class RV extends BaseClass {

	@Test
    public void checkForDBS() {
     
		WebElement docTitleElement = driver.findElement(By.xpath("//div[@class='doc-title']"));
        Assert.assertNotNull(docTitleElement, "Element with class 'doc-title' is not present.");
        String expectedText = "DBS Response.docx";
        String actualText = docTitleElement.getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedText, "Text does not match!");

	
	}
	
    
}
