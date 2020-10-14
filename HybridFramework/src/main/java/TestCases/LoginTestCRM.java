package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Pages.BaseClass;
import Pages.LoginPage;

public class LoginTestCRM extends BaseClass {
	
	@Test
	public void loginTest_ValidCredentials() {
		logger = report.createTest("Login to OrangeHRM");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		try {
			loginPage.orangeHRM_loginPage(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
			logger.pass("Login successfull");
		} catch (Exception e) {
			System.out.println("login is unsuccessful and the reason is:: "+ e.getMessage());
		}
		
	   logger.info("Test Completed!");
		
	}
}
