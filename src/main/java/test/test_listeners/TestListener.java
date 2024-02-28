package test.test_listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed, this is a message from ITestListener");
    }

}
