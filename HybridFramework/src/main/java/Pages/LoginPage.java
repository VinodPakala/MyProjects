package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//input[@id='txtUsername']") WebElement uname_xpath;
	@FindBy(xpath="//input[@id='txtPassword']") WebElement pword_xpath;
	@FindBy(xpath="//input[@id='btnLogin']") WebElement loginbtn_xpath;
	
	@FindBy(xpath="//a[@id='welcome']") WebElement welcome_btn;
	
	
	public void orangeHRM_loginPage(String uname_value, String pword_value) throws InterruptedException {
//		uname_xpath.clear();
		uname_xpath.sendKeys(uname_value);
		pword_xpath.sendKeys(pword_value);
		loginbtn_xpath.click();
		Thread.sleep(5000);
		if(welcome_btn.isDisplayed()) {
			System.out.println("Test pass, Hurry!!");
			Assert.assertTrue(true);
		}
		
	}
}
