package com.training.functional.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePage;
import com.training.pom.UniformLogoutPage;
import com.training.pom.UniformRegistrationPage;
import com.training.pom.UniformRegistrationSuccessPage;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UniformStoreRegistrationTestSuite extends TestBase{
	
	private UniformHomePage homePage;
	private UniformRegistrationPage registrationPage;	
	private UniformRegistrationSuccessPage registrationSuccessPage;
	private UniformLogoutPage logoutpage;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {	
		//Loading the Properties file	
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {	
		
		//Get Testcase Description
		String testDesc = properties.getProperty(method.getName());
		
		//Starting the Extent Reporter
		logger =eLog.startLogging(method.getName());
		logger.log(LogStatus.INFO, method.getName() + " : Executing " + testDesc);
		
		//Initializing the Driver
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		driver.manage().timeouts().implicitlyWait((long)Integer.parseInt(timeOut), TimeUnit.SECONDS);
		
		//Initialise Screenshot
		screenShot = new ScreenShot(driver);
		
		//Initializing the Pages

		homePage = new UniformHomePage(driver, logger);
		registrationPage = new UniformRegistrationPage(driver, logger);
		registrationSuccessPage = new UniformRegistrationSuccessPage(driver, logger);
		logoutpage = new UniformLogoutPage(driver, logger);
		
		//Launch the Application
		logger.log(LogStatus.PASS, "Launch Application URL : " + baseUrl);
		driver.get(baseUrl);
	}
	
	@Test(enabled=true)
	public void UNF_061(Method method) throws Exception {		  	
		
		//Testcase to Execute New User Registration Test
		logger.log(LogStatus.INFO, "Testcase to verify if subscription data is added successfully into DB");
		
		//Initialise the variables
		String user="email2002@gmail.com";
		String firstName="First2002";
		String lastName="Last2002";		
		String password="Pass2002";	
		String cPassword="Pass2002";	
		String phoneNumber="9890989751";	
		String faxNumber="9890989771";
		String companyName="IBM";
		String address1="DLF";
		String address2="Ramapuram";
		String cityName="Chennai";
		String postCode="600015";
		String countryName="India";
		String region="Tamil Nadu";
		String subscribe="No";
		
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("user", user);
		hm.put("password", password);	
		hm.put("cPassword", cPassword);	
		hm.put("firstName", firstName);
		hm.put("lastName", lastName);
		hm.put("phoneNumber", phoneNumber);	
		hm.put("faxNumber", faxNumber);
		hm.put("companyName", companyName);
		hm.put("address1", address1);
		hm.put("address2", address2);
		hm.put("cityName", cityName);
		hm.put("postCode", postCode);
		hm.put("countryName", countryName);
		hm.put("region", region);
		hm.put("subscribe", subscribe);
		
		ELearningDAO ed= new ELearningDAO();
		boolean status=ed.addUserAccount(hm);
		System.out.println("Database updated successfully : *******************************" + status );
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clickregister();
		
		//Verify Uniform Store Registration Page is launched before Registration and exit if not
		registrationPage.verifyRegistrationPageLaunched();
	
		//Fill up the Registration Form with various inputs
		registrationPage.typeFirstName(firstName);
		registrationPage.typeLastName(lastName);
		registrationPage.typeEmail(user);
		registrationPage.typeTelephone(phoneNumber);
		registrationPage.typeFax(faxNumber);
		registrationPage.typeCompany(companyName);
		registrationPage.typeAddress1(address1);
		registrationPage.typeAddress2(address2);
		registrationPage.typeCity(cityName);
		registrationPage.typePostcode(postCode);
		registrationPage.selectCountry(countryName);
		registrationPage.selctZone(region);
		registrationPage.typePassword(password);
		registrationPage.typeConfirmPassword(cPassword);
		registrationPage.subscribeNewsletter(subscribe);
		registrationPage.checkAgree();

		//Verify Entered User and Password and Proceed
		registrationPage.verifyEnteredFirstName();
		registrationPage.verifyEnteredEmail();	
		registrationPage.verifyEnteredPhoneNumber();
		registrationPage.verifyEnteredCity();
		registrationPage.verifyEnteredCountry();	
		registrationPage.verifyEnteredZone();		
		registrationPage.clickContinue();		
		
		//Verify Registration is Successful...
		registrationSuccessPage.verifyRegistrationSuccessful();
		registrationSuccessPage.verifyUserIsAddedInDB(user);
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(screenshotFolder + method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Registration");
	}
	
	@Test(dataProvider="dbUserData", dataProviderClass=LoginDataProviders.class, enabled=true)
	public void UNF_062(Method method, String firstName, String lastName, String emailId, String phoneNumber, String faxNumber, String company, String address1, String address2, String cityName, String postCode, String countryName, String region, String password, String cPassword, String subscribe) throws Exception {		  	
		
		//Testcase to Execute New User Registration Test
		logger.log(LogStatus.INFO, "Executing Multiple Successful Registration from DB Data provider");
		
		//Initialise the variables
		String user=emailId;
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clickregister();
		
		//Verify Uniform Store Registration Page is launched before Registration and exit if not
		registrationPage.verifyRegistrationPageLaunched();
	
		//Fill up the Registration Form with various inputs
		registrationPage.typeFirstName(firstName);
		registrationPage.typeLastName(lastName);
		registrationPage.typeEmail(user);
		
		registrationPage.typeTelephone(phoneNumber);
		registrationPage.typeAddress1(address1);
		registrationPage.typeAddress2(address2);
		registrationPage.typeCity(cityName);
		registrationPage.typePostcode(postCode);
		registrationPage.selectCountry(countryName);
		registrationPage.selctZone(region);
		registrationPage.typePassword(password);
		registrationPage.typeConfirmPassword(cPassword);
		registrationPage.subscribeNewsletter(subscribe);
		registrationPage.checkAgree();

		//Verify Entered User and Password and Proceed
		registrationPage.verifyEnteredFirstName();
		registrationPage.verifyEnteredEmail();	
		registrationPage.verifyEnteredPhoneNumber();
		registrationPage.verifyEnteredCity();
		registrationPage.verifyEnteredCountry();	
		registrationPage.verifyEnteredZone();		
		registrationPage.clickContinue();		
		
		//Verify Registration is Successful...
		registrationSuccessPage.verifyRegistrationSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(screenshotFolder + method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Registration");
	}
	
	@Test(dataProvider="xlUserData", dataProviderClass=LoginDataProviders.class, enabled=true)
	public void UNF_063(Method method, String firstName, String lastName, String emailId, String phoneNumber, String faxNumber, String company, String address1, String address2, String cityName, String postCode, String countryName, String region, String password, String cPassword, String subscribe) throws Throwable {		  	
		
		//Testcase to Execute New User Registration Test
		logger.log(LogStatus.INFO, "Executing Multiple Invalid Registration from Excel Data provider");
		
		//Initialise the variables
		String user=emailId;
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clickregister();
		
		//Verify Uniform Store Registration Page is launched before Registration and exit if not
		registrationPage.verifyRegistrationPageLaunched();
	
		//Fill up the Registration Form with various inputs
		registrationPage.typeFirstName(firstName);
		registrationPage.typeLastName(lastName);
		registrationPage.typeEmail(user);
		
		registrationPage.typeTelephone(phoneNumber);
		registrationPage.typeAddress1(address1);
		registrationPage.typeAddress2(address2);
		registrationPage.typeCity(cityName);
		registrationPage.typePostcode(postCode);
		registrationPage.selectCountry(countryName);
		registrationPage.selctZone(region);
		registrationPage.typePassword(password);
		registrationPage.typeConfirmPassword(cPassword);
		registrationPage.subscribeNewsletter(subscribe);
		registrationPage.checkAgree();

		//Verify Entered User and Password and Proceed
		registrationPage.verifyEnteredFirstName();
		registrationPage.verifyEnteredEmail();	
		registrationPage.verifyEnteredPhoneNumber();
		registrationPage.verifyEnteredCity();
		registrationPage.verifyEnteredCountry();	
		registrationPage.verifyEnteredZone();		
		registrationPage.clickContinue();		
		
		//Verify Registration is Successful...
		registrationSuccessPage.verifyRegistrationNotSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(screenshotFolder + method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Registration");
	}	
	
	@AfterMethod
	public void logout() throws InterruptedException {
		//Log out of Application
		try {
			logoutpage.clickLogout();
			logoutpage.verifyLogoutSuccessful();
			driver.quit();
			logger.log(LogStatus.PASS, "Close Application");
		}catch(Exception e) {
			logger.log(LogStatus.PASS, "Close Application");
			driver.quit();
		}finally {
			eLog.endLogging(logger);
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		eLog.generateReport();
	}	
}
