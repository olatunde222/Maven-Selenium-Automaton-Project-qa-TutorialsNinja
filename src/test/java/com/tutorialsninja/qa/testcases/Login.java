package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.utils.Utilities;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Login extends Utilities{
	
	
//	Declaring WebDriver globally
	WebDriver driver;
	
//	Creating a Setup / Before Method
	@BeforeMethod
	public void setup()
	{
//		Creating a logic for browser selection
		String BrowserName = "firefox";
		if (BrowserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (BrowserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(BrowserName.equals("safari")) {
			driver = new SafariDriver();
		}
		
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
//	Creating a TearDown / After Method
	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}
	
// (METHOD -1) Testing with Valid Credentials 
	@Test(priority = 1)
	public void verifyLoginWithValidCredentials()
	{
		
		driver.findElement(By.id("input-email")).sendKeys("gigamo4269@flexvio.com");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text() = 'My Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
//	(METHOD - 2) By providing the data using hard coded dataProvider
	
	@Test(dataProvider = "testDataSupply")
	public void veryLogin(String email, String password) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text() = 'My Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	@DataProvider
	public Object[][] testDataSupply(){
		Object [][] data = {{"gigamo4269@flexvio.com", "123456"},
				{"amotooricap1@gmail.com","12345"},
				{"amotooricap1@gmail.com","12345"}};
		return data;
	} 
		
	
//	(METHOD - 3) By Reading the data FROM EXCEL FILE DIRECTLY
	
	@Test(dataProvider = "excelDataSupply")
	public void veryLoginWithEcxelData(String email, String password) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text() = 'My Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	@DataProvider
	public Object[][] excelDataSupply(){
		Object [][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	} 
	
	
	
//	Testing with invalid credentials 
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException
	{
		driver.findElement(By.id("input-email")).sendKeys("xyzabc123"+generateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("xyzabc123");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected Warning Message");
		
		Thread.sleep(5000);
		
		
	}
	
//	Testing using invalid email and valid password 
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException
	{

		driver.findElement(By.id("input-email")).sendKeys("xyzabc123"+generateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected Warning Message");
		
		Thread.sleep(5000);
		
	}
	
//	Testing Using Valid Email and Invalid Password
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException
	{
		
		driver.findElement(By.id("input-email")).sendKeys("gigamo4269@flexvio.com");
		driver.findElement(By.id("input-password")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected Warning Message");
		
		Thread.sleep(5000);
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingAnyCredentials() throws InterruptedException
	{

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected Warning Message");
		
		Thread.sleep(5000);
		
	}
	@Test(priority = 6)
	public void verifyForgotPasswordLinkIsAvailableOnLoginPageAndWorking()
	{
	
		driver.findElement(By.linkText("Forgotten Password")).click();
		
		String forgotPasswordActualMessage = driver.findElement(By.id("content")).getText();
		String forgotPasswordExpectedMessage = "Forgot Your Password?";
		
		Assert.assertTrue(forgotPasswordActualMessage.contains(forgotPasswordExpectedMessage));
		
				
	}
	
	
	
//	Creating a function/method for generating a time stamp in string format to be appended in the login
//	of the invalid credentials to avoid failure of test when the allowed attempt of same invalid
//	credentials is exceeded.
	
	public String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace("" , "_").replace(":", "_");
	}
}
