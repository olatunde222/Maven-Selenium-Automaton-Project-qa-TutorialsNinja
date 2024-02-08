package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;

public class Search extends Base {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndApplicationURL("firefox");
	}
	@Test(priority = 1)
	public void VerifySearchingWithAnExistingProductName()
	{
		driver.findElement(By.xpath("//input[@name='search'][@placeholder = 'Search']")).sendKeys("iMac");
		driver.findElement(By.xpath("//button[@type='button'][@class = 'btn btn-default btn-lg']")).click();
		
		
		String searchMessage = driver.findElement(By.id("content")).getText();
		Assert.assertTrue(searchMessage.contains("Search - iMac"), "Search Product not displayed");
	}
	
	@Test(priority =2)
	public void VerifySearchingWithNonExistingProductName()
	{
		driver.findElement(By.xpath("//input[@name='search'][@placeholder = 'Search']")).sendKeys("fitbit");
		driver.findElement(By.xpath("//button[@type='button'][@class = 'btn btn-default btn-lg']")).click();
		
		String notExistingProductMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
		Assert.assertTrue(notExistingProductMessage.equals("There is no product that matches the search criteria."), "No message Displayed");
	}
	
	@Test(priority = 3)
	public void VerifySearchingWithoutProvidingAnyProductName()
	{
		driver.findElement(By.xpath("//input[@name='search'][@placeholder = 'Search']")).sendKeys("");
		driver.findElement(By.xpath("//button[@type='button'][@class = 'btn btn-default btn-lg']")).click();
		
		String notExistingProductMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
		Assert.assertTrue(notExistingProductMessage.equals("There is no product that matches the search criteria."), "No message Displayed");
	}
	
	
	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}
}
