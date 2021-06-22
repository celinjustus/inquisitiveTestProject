package com.inquisitive.test.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.inquisitive.test.app.setup.base;
import com.inquisitive.test.utils.ExtentReporterUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListeners extends base implements ITestListener {
    ExtentReports extent= ExtentReporterUtil.extentReportGenerator();
    ExtentTest test;
    WebDriver driver=null;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult iTestResult) {
        test= extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    test.log(Status.PASS,"Test Successful");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        extentTest.get().fail(iTestResult.getThrowable());
        //Object testObject = iTestResult.getInstance();
        //Class class1= iTestResult.getTestClass().getRealClass();

        try {
           //driver=(WebDriver)class1.getDeclaredField("driver").get(testObject);
           String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            String destination = System.getProperty("user.dir") + "/test-output/"+ iTestResult.getMethod().getMethodName()+dateName+".png";
            File DestFile=new File(destination);
            FileUtils.copyFile(SrcFile, DestFile);
            //extentTest.get().addScreenCaptureFromPath(destination,iTestResult.getMethod().getMethodName());
           } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP,"Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    extent.flush();
    }
}
