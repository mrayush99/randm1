package com.training.functional.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentTest;
import com.training.generics.ScreenShot;
import com.training.utility.ExtentLogger;

public class TestBase {
	
	protected static final ExtentLogger eLog= new ExtentLogger();
	protected static ExtentTest logger;
	protected static String baseUrl;
	protected static String timeOut;
	protected static Properties properties;
	protected static WebDriver driver;
	protected static ScreenShot screenShot;
	
    @BeforeSuite
    public void initBrowser() {
        //bw.init();
    }
    
	@BeforeTest
	public void initBrowser1() throws Exception {
		//Loading the Properties file
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		//Get the base url from the property file
		baseUrl = properties.getProperty("baseURL");
		timeOut=properties.getProperty("implicitWait");	
	}
    
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		eLog.generateReport();
	}	
	
    @AfterSuite
    public void terminateBrowser() {
        //bw.terminate();
    }	
}
