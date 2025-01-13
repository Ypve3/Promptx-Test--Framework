//package genericLib;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//public class Screenshot {
//
//    public String getscreenshot(WebDriver driver, String testName) throws IOException {
//        // Generate timestamp for unique screenshot names
//        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File src = ts.getScreenshotAs(OutputType.FILE);
//
//        // Save the screenshot in the designated folder
//        String screenshotPath = AutoConstant.photoPath + testName + "_" + timestamp + ".png";
//        File dest = new File(screenshotPath);
//        FileUtils.copyFile(src, dest);
//
//        return screenshotPath; // Return the screenshot path for the report
//    }
//}


package genericLib;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

    public String getscreenshot(WebDriver driver, String testName) throws IOException {
        
        Date date = new Date();
        String timestamp = date.toString().replaceAll(":", "-").replaceAll(" ", "_");

       
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        
        String destPath = AutoConstant.photoPath + testName + "_" + timestamp + ".png";

        
        File dest = new File(destPath);
        FileUtils.copyFile(src, dest);

       
        return dest.getAbsolutePath();
    }
}
