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

public class UniformShoppingCartPage {
	private WebDriver driver; 
	private WebDriverWait wait;
	private String title ="Shopping Cart";
	ExtentTest logger;
		
	public UniformShoppingCartPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='content']/div[2]/div[2]/a")
	private WebElement checkoutButton; 
	
	public void clickCheckout() throws InterruptedException {
		this.checkoutButton.click();
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, "Click Checkout Button");
	}	
	
	public void verifyShoppingCartDisplayed() throws Throwable {
		String expected=this.title;
		String actual;
		try{
			wait.until(ExpectedConditions.titleIs(this.title));
			actual=this.driver.getTitle();
			Assert.assertEquals(actual, expected);
			logger.log(LogStatus.PASS, "Verify Shopping Cart is displayed");
		}catch(Throwable t) {
			logger.log(LogStatus.FAIL, "Verify Shopping Cart is displayed");
			throw new Throwable(t);
		}	
	}
	
	public void verifyProductIsDisplayed() throws Throwable {
		logger.log(LogStatus.PASS, "Verify Product color, name, etc...");	
	}		
}
