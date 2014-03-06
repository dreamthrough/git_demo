package net.maven.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebDriverDemoTest{
	private WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setupBrowser(String browser){
		if (browser.equals("firefox")){
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")){
			driver = new ChromeDriver();
		} else {
			driver = new InternetExplorerDriver();
		}
	}
	
	@Parameters({ "url", "keyword" })
	@Test
	public void testSearch(String url,String keyword) throws Exception {
		this.driver.get(url);
		Thread.sleep(1000);
		
		WebElement input = driver.findElement(By.id("kw1"));
		input.sendKeys(keyword);
		Thread.sleep(1000);
		
		WebElement search = driver.findElement(By.id("su1"));
		search.click();
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.getTitle().contains(keyword),"Title tittle is incorrect.");
		System.out.println(driver.getTitle());
		
//		(new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
//			public Boolean apply(WebDriver d){
//	               return d.getTitle().startsWith(keyword);
//			}
//		});
	}
 
	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}
}
