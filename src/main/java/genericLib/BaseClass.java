package genericLib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public static ExtentReports reports;
    public ExtentSparkReporter sparkReporter;
    public static ExtentTest test;
    private static final Object lock = new Object();

    @BeforeSuite
    public void configBs() {
        System.out.println("BeforeSuite - Initializing ExtentReports...");
        synchronized (lock) {
            if (reports == null) {
                // Create reports directory if it doesn't exist
                File reportsDir = new File(AutoConstant.reportspath);
                if (!reportsDir.exists()) {
                    reportsDir.mkdirs();
                }
                
                sparkReporter = new ExtentSparkReporter(AutoConstant.reportspath + "/TestReport.html");
                sparkReporter.config().setDocumentTitle("Automation Report");
                sparkReporter.config().setReportName("Extent Report for Automation");

                reports = new ExtentReports();
                reports.attachReporter(sparkReporter);
                reports.setSystemInfo("Environment", "QA");
                reports.setSystemInfo("Tester", "Yash P");
            }
        }
    }

    @BeforeMethod
    public void openApp() throws FileNotFoundException {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(pdata.getData("url"));
            
            // Add explicit wait for better stability
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement emailField = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Email']"))
            );
            emailField.sendKeys("Yash.Pardeshi@gmail.com");
            
            WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='input password']"))
            );
            passwordField.sendKeys("123456");
            
            WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Login']"))
            );
            loginButton.click();
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            System.out.println("Failed to initialize browser: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void closeApp(ITestResult result) throws IOException {
        synchronized (lock) {
            try {
                String testName = result.getName();
                if (reports != null) {
                    test = reports.createTest(testName);
                    
                    if (result.getStatus() == ITestResult.FAILURE) {
                        test.log(Status.FAIL, testName + " - Test Case Failed");
                        test.log(Status.FAIL, result.getThrowable());
                        
                        // Capture and attach screenshot for failure
                        if (driver != null) {
                            Screenshot screenshot = new Screenshot();
                            String screenshotPath = screenshot.getscreenshot(driver, testName);
                            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                        }
                    } else if (result.getStatus() == ITestResult.SUCCESS) {
                        test.log(Status.PASS, testName + " - Test Case Passed");
                    } else if (result.getStatus() == ITestResult.SKIP) {
                        test.log(Status.SKIP, testName + " - Test Case Skipped");
                    }
                }
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }

    @AfterSuite
    public void configAs() {
        synchronized (lock) {
            if (reports != null) {
                reports.flush();
                reports = null; 
            }
        }
    }
}