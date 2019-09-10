package com.training.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.bean.UserAccountBean;
import com.training.dao.ELearningDAO;

public class UniformRegistrationSuccessPage {
	
	private WebDriver driver; 
	private WebDriverWait wait;
	private String expectedMessage="Congratulations! Your new account has been successfully created!";
	ExtentTest logger;
	
	public UniformRegistrationSuccessPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//p[text()='Congratulations! Your new account has been successfully created!']")	
	private WebElement successMessage; 
	
	@FindBy(xpath="//*[text()='Continue']")	
	private WebElement continueBtn;
	
	public void verifyRegistrationSuccessful() throws Exception {
		String actual;
		String expected = expectedMessage;
		try{
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			actual =successMessage.getText();
			Assert.assertEquals(actual, expected, "Verify User Registion is Successful");
			logger.log(LogStatus.PASS, "Verify User Registion is Successful");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify User Registion is Successful");
			throw new Exception(e);
		}		
	}
	
	public void verifyRegistrationNotSuccessful() throws Throwable {
		try{
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			logger.log(LogStatus.FAIL, "Verify User Registion is Not Successful");
		}catch(Exception e) {
			logger.log(LogStatus.PASS, "Verify User Registion is Not Successful");
			throw new Exception(e);
		}
	}

	public void verifyUserIsAddedInDB(String user) throws Exception {
		String expected=user;
		String actual;
		try{
			ELearningDAO ed= new ELearningDAO();
			List<UserAccountBean> userData=ed.getUserAccount(user);
			actual=userData.get(0).getEmailId();
			Assert.assertEquals(actual, expected);
			logger.log(LogStatus.PASS, "Verify User is added into Database");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify User is added into Database");
			throw new Exception(e);
		}			
	}		
}
