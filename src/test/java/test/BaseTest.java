package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.FileReaders;
import utilities.ScreenShot;

import java.io.IOException;

public class BaseTest  implements ITestListener {

    protected WebDriver driver;
    protected String appPropertiesPath = "./src/main/resources/config.properties";


    @BeforeMethod
    public void setup(){
        var props = FileReaders.propertiesLoader(appPropertiesPath);
        var browser = props.getProperty("browser");
        if(browser.equals("chrome")){
            driver = new ChromeDriver();
        }else if(browser.equals("firefox")){
            driver = new FirefoxDriver();
        }else {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(props.getProperty("url"));
    }

    @AfterMethod
    public void turnDown(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            try {
                ScreenShot.takeSnapShotToSpecifiedFolder(driver,result.getTestClass().getName(),result.getMethod().getMethodName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        driver.quit();
    }





}
