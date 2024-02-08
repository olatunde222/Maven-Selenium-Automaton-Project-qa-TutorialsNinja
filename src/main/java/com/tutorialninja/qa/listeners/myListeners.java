package com.tutorialninja.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.extentReporter;

public class myListeners implements ITestListener{
	
	
	ExtentReports extentReport; 
	ExtentTest extentTest ; 
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = extentReporter.generteExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started Executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName+"started Executed successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println("Screenshot taken");
		System.out.println(testName+"Got Failed");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"Test Skipped");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
	
		System.out.println("Finished Executing");
	}

	
}
