package com.training.functional.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePage;
import com.training.pom.UniformLoginPage;
import com.training.pom.UniformLogoutPage;
import com.training.pom.UniformOrderPage;
import com.training.pom.UniformShoppingCartPage;
import com.training.pom.UniformStoreCheckoutPage;
import com.training.pom.UniformStoreCheckoutSuccessPage;
import com.training.pom.UniformUserAccountPage;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UniformStoreOrderingTestSuite extends TestBase {

	private UniformHomePage homePage;
	private UniformLoginPage loginPage;
	private UniformOrderPage orderPage;
	private UniformShoppingCartPage cartPage;
	private UniformStoreCheckoutPage checkoutPage;
	private UniformStoreCheckoutSuccessPage checkoutSuccessPage;
	private UniformUserAccountPage accountPage;
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
		logger.log(LogStatus.INFO, method.getName() + " : Executing " + testDesc );
		
		//Initializing the Driver
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		driver.manage().timeouts().implicitlyWait((long)Integer.parseInt(timeOut), TimeUnit.SECONDS);
		
		//Initialise Screenshot
		screenShot = new ScreenShot(driver);
		
		//Initializing the Pages
		homePage = new UniformHomePage(driver, logger);
		loginPage = new UniformLoginPage(driver, logger);
		accountPage=new UniformUserAccountPage(driver, logger);
		orderPage = new UniformOrderPage(driver, logger);
		cartPage = new UniformShoppingCartPage(driver, logger);
		checkoutPage = new UniformStoreCheckoutPage(driver, logger);
		checkoutSuccessPage = new UniformStoreCheckoutSuccessPage(driver, logger);
		logoutpage = new UniformLogoutPage(driver, logger);

		//Launch the Application
		logger.log(LogStatus.PASS, "Launch Application URL " + baseUrl);
		driver.get(baseUrl);
	}
	
	@Test(priority=5 , enabled=true)
	public void UFM_005(Method method) throws Throwable {	
		
		//Testcase to Order a product without login
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		
		//Select an Item as per the argument
		homePage.clickAnItem("REGULAR T-SHIRTS (Rust)");
		
		//Verify Order page is displayed and select chest size
		orderPage.verifyOrderScreenDisplayed("REGULAR T-SHIRTS (Rust)");
		orderPage.selectChestSize("40");
		
		//Add the selected product onto cart
		orderPage.clickAddToCart();
		
		//View the cart and proceed to checkout
		orderPage.clickCart();
		orderPage.clickViewCart();
		cartPage.verifyShoppingCartDisplayed();
		cartPage.clickCheckout();
		
		//Verify that the loginpage is displayed as user didnt loggedin before shopping
		loginPage.verifyLoginPageLaunched();
		
		screenShot.captureScreenShot(screenshotFolder + method.getName());
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
		orderPage.selectChestSize("40");
		
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
		screenShot.captureScreenShot(screenshotFolder + method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Order Success page");	
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
}
