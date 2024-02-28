package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.FileReaders;
import utilities.ScreenShot;

import java.io.IOException;

public class BaseTest implements ITestListener {

    protected WebDriver driver;
    protected String appPropertiesPath = "./src/main/resources/config.properties";
    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentTest;

    @BeforeSuite
    public void beforeSuite() {
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuite() {
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup(ITestResult result) {
        var props = FileReaders.propertiesLoader(appPropertiesPath);
        var browser = props.getProperty("browser");
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(props.getProperty("url"));
        extentTest = extentReports.createTest("Test: %s".formatted(result.getMethod().getMethodName()));
    }

    @AfterMethod
    public void turnDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                var path = ScreenShot.takeSnapShotToSpecifiedFolder(driver, result.getTestClass().getName(), result.getMethod().getMethodName());
                extentTest.addScreenCaptureFromPath(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        driver.quit();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed: " + result.getName());
        extentTest.log(Status.FAIL, "Error Details: " + result.getThrowable());
    }
}
