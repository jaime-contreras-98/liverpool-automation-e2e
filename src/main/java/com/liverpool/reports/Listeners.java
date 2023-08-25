package com.liverpool.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.liverpool.helpers.BaseContext;
import com.liverpool.reports.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseContext implements ITestListener {

    ExtentTest test;
    ExtentReports extentRep = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extentRep.createTest(result.getMethod().getMethodName()); //scenario.getName() FOR CUCUMBER
        extentThread.set(test); // each thread has it's unique id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentThread.get().log(Status.PASS, "¡Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentThread.get().fail(result.getThrowable());

        try{
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch(Exception e1){
           e1.printStackTrace();
        }

        String filePath= null;
        try{
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch(IOException e2) {
            e2.printStackTrace();
        }

        extentThread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentRep.flush();
    }

}
