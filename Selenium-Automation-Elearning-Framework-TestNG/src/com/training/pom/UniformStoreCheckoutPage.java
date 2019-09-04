package com.training.pom;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.trianing.waits.WaitTypes;

public class UniformStoreCheckoutPage {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="My Account";
	private WaitTypes wt;
	ExtentTest logger;	
		
	public UniformStoreCheckoutPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='payment-existing']/select")
	private WebElement billingAddress;

	@FindBy(id="button-payment-address")
	private WebElement billingAddressContinue; 
	
	@FindBy(xpath="//*[@id='shipping-existing']/select")
	private WebElement shippingAddress;
	
	@FindBy(id="button-shipping-address")
	private WebElement shippingAddressContinue; 
	
	@FindBy(id="shipping_method")
	private WebElement shippingMethod;
	
	@FindBy(id="button-shipping-method")
	private WebElement shippingMethodContinue; 		
	
	@FindBy(id="button-payment-method")
	private WebElement paymentMethodContinue; 	
	
	@FindBy(name="comment")
	private WebElement orderComment; 	

	@FindBy(name="agree")
	private WebElement orderAgreement; 
	
	@FindBy(id="button-confirm")
	private WebElement confirmOrder; 
	
	public void clickConfirmOrder() throws Exception {
		
		wt = new WaitTypes(this.driver);
		WebElement ele = wt.elementToBeClickable(confirmOrder, 5);		
		ele.click(); 
		logger.log(LogStatus.PASS, "Confirm Order");
	}		
	
	public void clickPaymentMethodContinue() throws Exception {
		paymentMethodContinue.click();
		logger.log(LogStatus.PASS, "Click Payment Method Continue");
	}		
	
	public void clickShippingMethodContinue() throws Exception {
		shippingMethodContinue.click();
		logger.log(LogStatus.PASS, "Click Shipping Method Continue");
	}	
	
	public void clickShippingAddressContinue() throws Exception {
		
		wt = new WaitTypes(this.driver);
		WebElement ele = wt.elementToBeClickable(shippingAddressContinue, 5);		
		ele.click(); 
		logger.log(LogStatus.PASS, "Click Shipping Address Continue");
	}
	
	public void clickBillingAddressContinue() throws Exception {
		billingAddressContinue.click();
		logger.log(LogStatus.PASS, "Click Billing Address Continue");
	}
	
	public void agreeTermsAndCondition() {
		wt = new WaitTypes(this.driver);
		WebElement ele = wt.elementToBeClickable(orderAgreement, 5);		
		ele.click(); 
		logger.log(LogStatus.PASS, "Agree Terms and Condition");
	}		
	
	public void enterOrderComment() throws Exception {
		orderComment.sendKeys("Please Deliver between 7am to 10 am");
		logger.log(LogStatus.PASS, "Enter Order Comment : Please Deliver between 7am to 10 am");
	}	
	
	public void verifyFreeShippingSelected() throws Exception {
		boolean freeSelected=shippingMethod.isSelected();
		if(freeSelected) {
			logger.log(LogStatus.PASS, "Verify Free Shipping is Selected");
		}else {
			shippingMethod.click();
			logger.log(LogStatus.PASS, "Verify Free Shipping is Selected");
		}
	}
	
	public void verifyBillingAddressDisplayed() throws Exception {
		Select shirtSize = new Select(billingAddress);
		String selectedAddress=shirtSize.getFirstSelectedOption().getText();
		logger.log(LogStatus.PASS, "Verify Billing Address is Diplayed : Displayed Address is : " + selectedAddress);
	}
}
