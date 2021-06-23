package com.inquisitive.test.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.inquisitive.test.app.setup.base;
import com.inquisitive.test.utils.ExtentReporterUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners extends base implements ITestListener {
    ExtentReports extent= ExtentReporterUtil.extentReportGenerator();
    ExtentTest test;

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
