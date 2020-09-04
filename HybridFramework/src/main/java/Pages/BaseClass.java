package Pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Util.BrowserFactory;
import Util.ConfigDataProvider;
import Util.ExcelDataProvider;
import Util.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() {
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		ExtentHtmlReporter htmlReport= new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/OrangeHRM_"+ Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(htmlReport);
//		logger.info("Excel, Config and Reports are initialized");
	}
	
	@BeforeClass
	public void setUp() {
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getQaUrl());
//		 logger.info("Browser and app are up and running");
	}
	
	@AfterClass
	public void tearDown() {
		BrowserFactory.closeBrowser(driver);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
			try {
				logger.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.takeScreenshot(driver)).build());
			} catch (IOException e) {
				
			System.out.println("unable to take screenshot"+ e.getMessage());
			}
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			
			try {
				logger.pass("Test Pass", MediaEntityBuilder.createScreenCaptureFromPath(Helper.takeScreenshot(driver)).build());
			} catch (IOException e) {
				
			System.out.println("unable to take screenshot"+ e.getMessage());
			}
		}
		report.flush();
		
	}
	
}
