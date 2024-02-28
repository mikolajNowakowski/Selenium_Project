package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    private static final String screenShotPath = "./src/main/resources/screen_shot/";

    public static void takeSnapShotToSpecifiedFolder(WebDriver driver, String className, String testName) throws IOException {
        File destFile = new File(screenShotPath+className.replaceAll("\\.","/")+"/"+ testName+"_"+timestamp()+".png");
        takeSnapShot(driver,destFile);
    }

    public static void takeSnapShotToResources(WebDriver driver) throws IOException {
        File destFile = new File(screenShotPath+ timestamp()+".png");
        takeSnapShot(driver,destFile);
    }


    private static void takeSnapShot(WebDriver driver, File destFile) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }


    public static String timestamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

}
