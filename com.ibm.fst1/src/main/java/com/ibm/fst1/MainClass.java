package com.ibm.fst1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainClass {
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() {
		 WebDriverManager.chromedriver().setup();
	     driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	@AfterSuite
	public void quitBrowser() {
		driver.quit();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
