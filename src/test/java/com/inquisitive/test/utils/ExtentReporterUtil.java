package com.inquisitive.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtil {
    static ExtentReports extent;

    public static ExtentReports extentReportGenerator(){
        ExtentSparkReporter reporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport.html");
        reporter.config().setReportName("Automation Test Results");
        reporter.config().setDocumentTitle("Test Results");
        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA","Celin");
        return extent;
    }
}
