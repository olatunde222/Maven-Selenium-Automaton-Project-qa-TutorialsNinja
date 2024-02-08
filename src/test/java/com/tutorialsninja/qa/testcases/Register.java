package com.tutorialsninja.qa.testcases;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;

public class Register extends Base {
	
	
//	Declaring the driver globally
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndApplicationURL("chrome");
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test (priority = 1)
	public void VerifyRegisteringAnAccountByProvidingOnlyTheMandatoryFields() throws InterruptedException
	{
		driver.findElement(By.id("input-firstname")).sendKeys("automation");
		driver.findElement(By.id("input-lastname")).sendKeys("tester");
		driver.findElement(By.id("input-email")).sendKeys(emailTimeStampGenerator());
	
		driver.findElement(By.id("input-telephone")).sendKeys("0912345678");
		driver.findElement(By.id("input-password")).sendKeys("22222");
		driver.findElement(By.id("input-confirm")).sendKeys("22222");
		driver.findElement(By.xpath("//input[@name = 'newsletter'][@value = '1']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedSuccessMessage = "Your Account Has Been Created!";
		Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage);
	}
	
	@Test(priority = 2)
	public void VerifyProperNotificationMessagesAreDisplayedForTheMandatoryFields() 
	{
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys("");
	
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String errorMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertTrue(errorMessage.contains("Register Account"));
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning,"First Name must be between 1 and 32 characters!","First Name Warning Message is not Displayed");
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning,"Last Name must be between 1 and 32 characters!","Last Name Warning Message is not Displayed");
		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailWarning,"E-Mail Address does not appear to be valid!","Email Warning Message is not Displayed");
		
		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning,"Telephone must be between 3 and 32 characters!","Telephone Warning Message is not Displayed");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning,"Password must be between 4 and 20 characters!","Password Warning Message is not Displayed");
		
	}
	
	@Test (priority = 3)
	public void VerifyRegisteringAnAccountByEnteringDifferentPasswordsIntoPasswordAndConfirmFields() 
	{
		driver.findElement(By.id("input-firstname")).sendKeys("automation");
		driver.findElement(By.id("input-lastname")).sendKeys("tester");
		driver.findElement(By.id("input-email")).sendKeys(emailTimeStampGenerator());
	
		driver.findElement(By.id("input-telephone")).sendKeys("0912345678");
		driver.findElement(By.id("input-password")).sendKeys("22222");
		driver.findElement(By.id("input-confirm")).sendKeys("33333");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPasswordErrorMessage = driver.findElement(By.xpath("//div[@class='text-danger']")).getText();
		String expectedPasswordErrorMessage = "Password confirmation does not match password!";
		Assert.assertEquals(actualPasswordErrorMessage,expectedPasswordErrorMessage);
	}
	
	@Test(priority = 4)
	public void VerifyRegisteringAnAccountProvidingExistingAccountDetails()
	{
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail.com");
	
		driver.findElement(By.id("input-telephone")).sendKeys("09246812111");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	@Test(priority = 5)	
	public void VerifyRegisteringAnAccountByProvidingInvalidEmailAddressTotheEMailField()
	{
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail");
	
		driver.findElement(By.id("input-telephone")).sendKeys("09246812111");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[@class = 'text-danger']")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
//	Creating a method that will generate different time converted to a string to be used for generating
//	random email addresses and to be appended in the email input field 
	public String emailTimeStampGenerator()
	{
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "autotester"+timestamp+"@gmail.com";
	}
}
