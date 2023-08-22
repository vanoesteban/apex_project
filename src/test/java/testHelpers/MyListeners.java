package testHelpers;
import Controllers.CreateDriver;
import Controllers.Global_Vars;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class MyListeners implements ITestListener {

    @Override
    public void onStart(ITestContext contextStart) {
        //System.out.println("onStart method started");
    }

    @Override
    public void onFinish(ITestContext contextFinish) {
        //System.out.println("onFinish method finished");

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: "+ result.getName());

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: "+ result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        File screenshot = ((TakesScreenshot) CreateDriver.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(Global_Vars.SCREENSHOTS_PATH));
            System.out.println("::: Taking Screenshot :::");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Test failed: "+ result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: "+ result.getName());

    }



    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //System.out.println("Method failed with certain success percentage"+ result.getName());

    }

}
