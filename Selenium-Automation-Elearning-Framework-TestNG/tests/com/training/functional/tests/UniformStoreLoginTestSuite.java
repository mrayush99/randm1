package com.training.functional.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.UniformChangePasswordPage;
import com.training.pom.UniformHomePage;
import com.training.pom.UniformLoginPage;
import com.training.pom.UniformLogoutPage;
import com.training.pom.UniformRegistrationPage;
import com.training.pom.UniformRegistrationSuccessPage;
import com.training.pom.UniformUserAccountPage;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UniformStoreLoginTestSuite extends TestBase{
	
	private UniformHomePage homePage;
	private UniformRegistrationPage registrationPage;	
	private UniformRegistrationSuccessPage registrationSuccessPage;
	private UniformLogoutPage logoutpage;
	private UniformLoginPage loginPage;
	private UniformUserAccountPage accountPage;
	private UniformChangePasswordPage changePasswordPage;
	
	//==============change the below inputs for each run======================
	private String user="email62@gmail.com";
	private String password="Pass62";		
	//==============Inputs Ends Here======================	
	
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
		loginPage = new UniformLoginPage(driver, logger);
		registrationPage = new UniformRegistrationPage(driver, logger);
		registrationSuccessPage = new UniformRegistrationSuccessPage(driver, logger);
		changePasswordPage=new UniformChangePasswordPage(driver, logger);		
		accountPage=new UniformUserAccountPage(driver, logger);
		logoutpage = new UniformLogoutPage(driver, logger);
		
		//Launch the Application
		logger.log(LogStatus.PASS, "Launch Application URL " + baseUrl);
		driver.get(baseUrl);
	}
	
	@Test(priority=1 , enabled=true)
	public void UFM_001(Method method) throws Exception {	
		
		//Testcase to Execute New User Registration Test

		//Initialise the variables
		String firstName="First55";
		String lastName="Last55";
		String phoneNumber="9890989754";	
		String faxNumber="9890989771";
		String companyName="IBM";
		String address1="DLF";
		String address2="Ramapuram";
		String cityName="Chennai";
		String postcodeNumber="600015";
		String countryName="India";
		String zoneName="Tamil Nadu";
		String subscribe="No";
		
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
		registrationPage.typePostcode(postcodeNumber);
		registrationPage.selectCountry(countryName);
		registrationPage.selctZone(zoneName);
		registrationPage.typePassword(password);
		registrationPage.typeConfirmPassword(password);
		registrationPage.subscribeNewsletter(subscribe);
		registrationPage.checkAgree();

		//Verify Entered User and Password and Proceed
		registrationPage.verifyEnteredFirstName();
		registrationPage.verifyEnteredEmail();	
		registrationPage.verifyEnteredPhoneNumber();
		registrationPage.clickContinue();		
		
		//Verify Registration is Successful...
		registrationSuccessPage.verifyRegistrationSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Registration");
	}
	
	@Test(priority=2, enabled=true)
	public void UFM_002(Method method) throws Exception {	
		
		//Testcase to Verify Successful User Login
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	
		
		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(user);
		loginPage.typePassword(password);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();
		loginPage.clickLogin();
		
		//Verify login is Successful
		accountPage.verifyLoginIsSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Login");		
	}
	
	@Test(priority=3, enabled=true)
	public void UFM_003(Method method) throws Exception {		
		//Testcase to Execute Change Password Test
		
		String newPassword="Pass55";

		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	

		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(user);
		loginPage.typePassword(password);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();		
		loginPage.clickLogin();
		
		//Verify if Login is Successful and change the password
		accountPage.verifyLoginIsSuccessful();
		accountPage.clickChangePassword();
		changePasswordPage.typePassword(newPassword);
		changePasswordPage.typeConfirmPassword(newPassword);
		changePasswordPage.clickContinue();
		
		//Verify if password is changed successfully
		accountPage.verifyPasswordChangedSuccessful();

		//Take Screenshot of success password change
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of password change");			
	}
	
	@Test(priority=4, enabled=true)
	public void UFM_004(Method method) throws Throwable {		
		//Testcase to Execute Invalid login Test
		
		String invalidpassword="Pass53";

		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	

		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(user);
		loginPage.typePassword(invalidpassword);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();		
		loginPage.clickLogin();
		
		//Verify if Login Failed
		loginPage.verifyLoginFailed();

		//Take Screenshot of success password change
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Login Failure");			
	}	
	
/*	
	@Test(priority=5 , enabled=true)
	public void UFM_005(Method method) throws Throwable {	
		
		//Testcase to Order a product without login
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		
		//Select an Item as per the argument
		homePage.clickAnItem("REGULAR T-SHIRTS (Rust)");
		
		//Verify Order page is displayed and select chest size
		orderPage.verifyOrderScreenDisplayed("REGULAR T-SHIRTS (Rust)");
		orderPage.selectChestSize("38");
		
		//Add the selected product onto cart
		orderPage.clickAddToCart();
		
		//View the cart and proceed to checkout
		orderPage.clickCart();
		orderPage.clickViewCart();
		cartPage.verifyShoppingCartDisplayed();
		cartPage.clickCheckout();
		
		//Verify that the loginpage is displayed as user didnt loggedin before shopping
		loginPage.verifyLoginPageLaunched();
		
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of login page");	
	}
	
	@Test(priority=6 , enabled=true)
	public void UFM_006(Method method) throws Throwable {	
		
		//Testcase to Verify Successful Placement of an Order after pre login
		
		String user="email54@gmail.com";
		String password="Pass55";

		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	
		
		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(user);
		loginPage.typePassword(password);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();
		loginPage.clickLogin();
		
		//Verify login is Successful
		accountPage.verifyLoginIsSuccessful();
		accountPage.clickLogo();
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();	
		
		//Select an Item as per the argument
		homePage.clickAnItem("REGULAR T-SHIRTS (Rust)");
		
		//Verify Order page is displayed and select chest size
		orderPage.verifyOrderScreenDisplayed("REGULAR T-SHIRTS (Rust)");
		orderPage.selectChestSize("38");
		
		//Add the selected product onto cart
		orderPage.clickAddToCart();
		
		//View the cart and proceed to checkout
		orderPage.clickCart();
		orderPage.clickViewCart();
		cartPage.verifyShoppingCartDisplayed();
		cartPage.clickCheckout();
		
		//Confirm the Address, payment Method and place the order
		checkoutPage.clickBillingAddressContinue();
		checkoutPage.clickShippingAddressContinue();
		checkoutPage.clickShippingMethodContinue();
		checkoutPage.enterOrderComment();
		checkoutPage.agreeTermsAndCondition();		
		checkoutPage.clickPaymentMethodContinue();
		checkoutPage.clickConfirmOrder();
		
		//Verify Order is placed Successfully
		checkoutSuccessPage.verifyOrderSuccessPageDisplayed();
		checkoutSuccessPage.verifyOrderPlaced();
		
		//Take Screenshot of successful placement of Order		
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Order Success page");	
	}	
*/
	
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
