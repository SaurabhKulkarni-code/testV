package com.BaseClasses;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseClass {
	
	
	 WebDriver driver;
	 String dateIMDB;
	 String countryIMDB;
	 String dateWIKI;
	 String countryWIKI;
	 String Movie = "Pushpa: The Rise";
	
	
	
	@BeforeTest
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver","/Users/saurabh/Downloads/chromedriver_New");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
	}
	
	
	@Test
	public void searchIMDB() {
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.imdb.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.id("suggestion-search")).sendKeys(Movie);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//div[contains(text(),'2021')]")).click();
		WebElement Element = driver.findElement(By.xpath("//span[contains(text(),'Details')]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		String dateIMDB = driver.findElement(By.xpath("//li[@data-testid='title-details-releasedate']")).getText();
		String countryIMDB = driver.findElement(By.xpath("//li[@data-testid='title-details-origin']")).getText();
		System.out.println(dateIMDB);
		System.out.println(countryIMDB);
		
		
	}
	
	
	@Test
	public void searchWIKI() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/");
		driver.findElement(By.id("searchInput")).sendKeys(Movie);
		driver.findElement(By.xpath("//input[@id='searchButton']")).click();
		String dateWIKI = driver.findElement(By.xpath("//table[@class='infobox vevent']/tbody[1]/tr[12]")).getText();
		String countryWIKI = driver.findElement(By.xpath("//table[@class='infobox vevent']/tbody[1]/tr[14]")).getText();
		System.out.println(dateWIKI);
		System.out.println(countryWIKI);
		
		
	}
	
	@Test
	public void compareInfo() {
		
	
		if ( (countryIMDB.contains("India") && countryWIKI.contains("India")) && 
				(dateIMDB.contains("December") && dateWIKI.contains("December") ) )  {
				
			
			System.out.println("Info is Same");
			
		}
		
		else {
			System.out.println("Info is not same");
			assertTrue(false);
		}
	
		
	}
	
	@AfterTest
	public void clsoeBrowser() {
		
		driver.quit();
	}
	
	


}
