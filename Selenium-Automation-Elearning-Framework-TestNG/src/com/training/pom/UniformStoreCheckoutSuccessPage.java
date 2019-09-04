package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UniformStoreCheckoutSuccessPage {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="Your order has been placed!";
	ExtentTest logger;
		
	public UniformStoreCheckoutSuccessPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='content']/h1")
	private WebElement orderPlaced;
	
	public void verifyOrderPlaced() throws Throwable {
		String expected="Your order has been placed!";
		String actual;
		try{
			wait.until(ExpectedConditions.visibilityOf(orderPlaced));
			actual=orderPlaced.getText();
			Assert.assertEquals(actual, expected.toUpperCase());
			logger.log(LogStatus.PASS, "Verify Order placed : " + actual);
		}catch(Throwable t) {
			logger.log(LogStatus.FAIL, "Verify Order placed");
			throw new Throwable(t);
		}	
	}	
	
	public void verifyOrderSuccessPageDisplayed() throws Throwable {
		String expected=title;
		String actual;
		try{
			wait.until(ExpectedConditions.titleIs(expected));
			actual=this.driver.getTitle();
			Assert.assertEquals(actual, expected);
			logger.log(LogStatus.PASS, "Verify Order Success Page displayed");
		}catch(Throwable t) {
			logger.log(LogStatus.FAIL, "Verify Order Success Page displayed");
			throw new Throwable(t);
		}	
	}		
}
