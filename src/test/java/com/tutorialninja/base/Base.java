package com.tutorialninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
//	Global declarations for accessibility
	public Properties prop;
	public Properties testDataProp;
	
//	Method for loading the conFig file BUT I WONT BE USING IT IN THIS PROJECT. 
	public void loadProperties() 
	{
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
//	Method for loading the hard coded test data File BUT I WONT BE USING IT IN THIS PROJECT 
	public void loadTestData() {
		testDataProp = new Properties();
		File testDataFile  = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties"); 
		try {
			FileInputStream testFIS = new FileInputStream(testDataFile);
			testDataProp.load(testFIS);
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
//	Method for WebDriver Selection 	
	public WebDriver initializeBrowserAndApplicationURL(String browserName) {
//		Creating a logic for browser selection
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get("https://tutorialsninja.com/demo/");
		
		return driver;
	}

}
