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
import com.training.utility.ExtentLogger;
import com.trianing.waits.WaitTypes;

public class UniformHomePage {

	private WebDriver driver; 
	private String title="Uniform Store";
	private WaitTypes wt;
	private WebDriverWait wait;
	ExtentTest logger;
	private String selectedItem;
		
	public UniformHomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement account; 

	@FindBy(xpath="//a[text()='Register']")
	private WebElement register; 
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement login; 	
	
	@FindBy(xpath="//*[@class='img-responsive' and @alt='banner1']")
	private WebElement banner; 
	
	@FindBy(xpath="//*[@title='REGULAR T-SHIRTS (YELLOW)'] and @class='img-responsive']")
	private WebElement regularYellow; 
	
	@FindBy(xpath="//*[@title='REGULAR T-SHIRTS (Rust)' and @class='img-responsive']")
	private WebElement regularRust;
	
	@FindBy(xpath="//*[@title='Regular T-Shirt (Maroon)']")
	private WebElement regularMaroon;	

	public void clickBanner() {
		this.banner.click();
		logger.log(LogStatus.PASS, "Click Account");
	}	
	
	public void clickAccount() {
		this.account.click();
		logger.log(LogStatus.PASS, "Click Account");
	}	
	
	public void clickregister() {
		wait.until(ExpectedConditions.elementToBeClickable(register));
		this.register.click(); 
		logger.log(LogStatus.PASS, "Click Register");
	}	

	public void clicklogin() {
		wait.until(ExpectedConditions.elementToBeClickable(login));
		this.login.click(); 
		logger.log(LogStatus.PASS, "Click Login Link");
	}	

	public void verifyHomePageLaunched() throws Exception {
		WaitTypes wt = new WaitTypes(this.driver);
		boolean expected=true;
		boolean actual;
		try{
			WebElement ele = wt.elementToBeClickable(account, 5);
			actual=ele.isDisplayed();
			Assert.assertEquals(expected, actual, "Verify Homepage is Launched");
			logger.log(LogStatus.PASS, "Verify Homepage is Launched");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Homepage is Launched");
			throw new Exception(e);
		}
	}	
	
	public void verifyBannerDisplayed() throws Exception {
		WaitTypes wt = new WaitTypes(this.driver);
		boolean expected=true;
		boolean actual;
		try{
			WebElement ele = wt.elementToBeClickable(banner, 5);
			actual=ele.isDisplayed();
			Assert.assertEquals(expected, actual, "Banner is Launched");
			logger.log(LogStatus.PASS, "Verify Banner is Displayed");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Banner is Displayed");
			throw new Exception(e);
		}
	}	
	
	public void verifyOrderScreenDisplayed() throws Throwable {
		String expected=this.selectedItem;
		String actual;
		try{
			wait.until(ExpectedConditions.titleIs(expected));
			actual=this.driver.getTitle();
			Assert.assertEquals(actual, expected);
			logger.log(LogStatus.PASS, "Verify Order Screen displayed : Order Screen displayed for : " + actual);
		}catch(Throwable t) {
			logger.log(LogStatus.FAIL, "Verify Order Screen displayed");
			throw new Throwable(t);
		}	
	}	
	
	public void clickAnItem(String color) throws InterruptedException {
		this.selectedItem=color;
		if(this.selectedItem.equals("REGULAR T-SHIRTS (YELLOW)")){
			wait.until(ExpectedConditions.elementToBeClickable(regularYellow));
			this.regularYellow.click();
			logger.log(LogStatus.PASS, "Select REGULAR T-SHIRTS (YELLOW)");
		}else if(this.selectedItem.equals("REGULAR T-SHIRTS (Rust)")){
			wait.until(ExpectedConditions.elementToBeClickable(regularRust));
			this.regularRust.click();
			logger.log(LogStatus.PASS, "Select REGULAR T-SHIRTS (Rust)");
		}else if(this.selectedItem.equals("REGULAR T-SHIRTS (Maroon)")){
			wait.until(ExpectedConditions.elementToBeClickable(regularMaroon));
			this.regularMaroon.click();
			logger.log(LogStatus.PASS, "Select REGULAR T-SHIRTS (Maroon)");
		}
	}			
}