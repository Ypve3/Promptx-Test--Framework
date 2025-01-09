//package genericLib;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class BaseClass {
//
//	
//	public WebDriver driver;
//	public Propertyfile pdata=new Propertyfile();
//	public ExtentReports reports;
//	public ExtentHtmlReporter htmlreport;
//	public static ExtentTest test;
//    	
//	
////	@BeforeSuite
////	public void configBs() {
////	
////	ExtentHtmlReporter htmlreport= new ExtentHtmlReporter(AutoConstant.reportspath);
////	htmlreport.config().setDocumentTitle("Promptx");
////	htmlreport.config().setTheme(Theme.DARK);
////	ExtentReports reports= new ExtentReports();
////	reports.attachReporter(htmlreport);
////	}
//
//	@BeforeSuite
//    public void configBs() {
//        // Setup ExtentReports
//        htmlReporter = new ExtentHtmlReporter(AutoConstant.reportspath + "/TestReport.html");
//        htmlReporter.config().setDocumentTitle("Automation Report");
//        htmlReporter.config().setTheme(Theme.DARK);
//
//        reports = new ExtentReports();
//        reports.attachReporter(htmlReporter);
//        reports.setSystemInfo("Environment", "QA");
//        reports.setSystemInfo("Tester", "Your Name");
//    }
//	
////	@BeforeMethod
////	public void openApp() throws FileNotFoundException {
////	    WebDriverManager.chromedriver().setup();
////		driver=new ChromeDriver();
////		driver.manage().window().maximize();
////		driver.get(pdata.getData("url"));
////		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
////		
////      }
//	@BeforeMethod
//	public void openApp() throws IOException {
//	    WebDriverManager.chromedriver().setup();
//	    driver = new ChromeDriver();
//	    driver.manage().window().maximize();
//	    String url = pdata.getData("url"); // Fetch URL from properties
//	    if (url == null || url.isEmpty()) {
//	        throw new RuntimeException("URL is not specified in the properties file!");
//	    }
//	    driver.get(url);
//	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//	}
//
//	
//	@AfterMethod
//	public void closeApp(ITestResult res) throws IOException {
//		
//		int status=res.getStatus();
//		String name = res.getName();
//		
//		if(status==ITestResult.FAILURE) {
//			test.log(Status.FAIL, res.getName() + "Test Case Failed");
//			test.log(Status.FAIL, res.getThrowable()+ "Test Case Failed");
//		}
//		else if(status==ITestResult.SUCCESS) {
//			test.log(Status.PASS, res.getName() + "Test Case Passed");
//		}
//		else if(status==ITestResult.SKIP) {
//			test.log(Status.SKIP, res.getName() + "Test Case Skipped");
//		}
//		if(status==2) {
//			Screenshot s= new Screenshot();
//			s.getscreenshot(driver, name);
//		}
//		
//		
//	}
//	
//	@AfterSuite
//	public void configAs() {
//		//htmlreport.flush();
//		reports.flush();
//	}
//	
//}


package genericLib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public WebDriver driver;
    public Propertyfile pdata = new Propertyfile();
    public ExtentReports reports;
    public ExtentSparkReporter sparkReporter;
    public static ExtentTest test;

    @BeforeSuite
    public void configBs() {
        
        sparkReporter = new ExtentSparkReporter(AutoConstant.reportspath + "/TestReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Extent Report for Automation");
        

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("Tester", "Yash P");
    }

    @BeforeMethod
    public void openApp() throws FileNotFoundException {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(pdata.getData("url"));
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("Yash.Pardeshi@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='input password']")).sendKeys("123456");
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    
    
    
    @AfterMethod
    public void closeApp(ITestResult result) throws IOException {
    	String testName = result.getName();
        Screenshot screenshot = new Screenshot();

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, testName + " - Test Case Failed");
            test.log(Status.FAIL, result.getThrowable());

            String screenshotPath = screenshot.getscreenshot(driver, testName);
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, testName + " - Test Case Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, testName + " - Test Case Skipped");
        }
        
        if (driver != null) {
            driver.quit(); 
        }
    }

    @AfterSuite
    public void configAs() {
        reports.flush(); 
    }
}
