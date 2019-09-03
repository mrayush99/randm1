package com.training.pom;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UniformOrderPage {
	private WebDriver driver; 
	private WebDriverWait wait;
	ExtentTest logger;
		
	public UniformOrderPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(@id, 'input-option')]")
	private WebElement chestSize; 
	
	@FindBy(xpath="//*[@id='button-cart']")
	private WebElement addCartButton;
	
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement addCartSuccess;
	
	@FindBy(xpath="//*[@id='cart']")
	private WebElement cartButton;	
	
	@FindBy(xpath="//*[@id='cart']/ul/li[2]/div/p/a[1]")
	private WebElement viewCartButton;	
	
	@FindBy(xpath="//*[@id='cart']/ul/li[2]/div/p/a[2]")
	private WebElement checkoutButton;	

	public void clickCart() throws InterruptedException {
		Actions action = new Actions(this.driver);
		action.moveToElement(cartButton).build().perform();
		action.click().build().perform();
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, "Click cart Button");
	}	
	
	public void clickViewCart() throws InterruptedException {
		Actions action = new Actions(this.driver);
		action.moveToElement(this.viewCartButton).build().perform();
		action.click().build().perform();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Click View cart Button");
	}		
	
	public void clickCheckoutButton() throws InterruptedException {
		this.checkoutButton.click();
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, "Click Checkout Button");
	}	
	
	public void clickAddToCart() {
		this.addCartButton.click();
		logger.log(LogStatus.PASS, "Click Add to cart Button");
	}	
	
	public void selectChestSize(String mySize) {
		Select shirtSize = new Select(chestSize);
		List<WebElement> options = shirtSize.getOptions();
		Iterator<WebElement> sizes = options.iterator();
		boolean flag=false;
		while(options.iterator().hasNext()) {
			String size=sizes.next().getText().trim();
			if(size.contains(mySize)) {
				shirtSize.selectByVisibleText(size);
				flag=true;
				break;
			}
		}
		if(flag) {
			logger.log(LogStatus.PASS, "Select the Shirt Size");
		}else {
			logger.log(LogStatus.FAIL, "Select the Shirt Size");
		}
	}
	
	public void verigyOrderPageDisplayed() {
		logger.log(LogStatus.PASS, "Verify Order Page is Displayed");
	}
	
	public void verifyItemAddedToCart() throws Throwable {
		String expectedText="Success: You have added";
		String actualText;
		boolean expected=true;
		boolean actual;
		try{
			wait.until(ExpectedConditions.visibilityOf(addCartSuccess));
			actualText=addCartSuccess.getText();
			actual=addCartSuccess.getText().contains(expectedText);
			Assert.assertEquals(actual, expected);
			logger.log(LogStatus.PASS, "Verify Item added to Cart : Item Added with Message : " + actualText);
		}catch(Throwable t) {
			logger.log(LogStatus.FAIL, "Verify Login is Failed");
			throw new Throwable(t);
		}	
	}	
}
