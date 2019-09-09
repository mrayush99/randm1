package com.training.functional.tests;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;

public class DataProviderTest {
/*	  @Test(dataProvider="login", dataProviderClass=LoginDataProviders.class)
	  public void excelDataProviderTest(String user, String password) {
		  String screenshotFolder=System.getProperty("user.dir");
		  System.out.println("User " + user + " Password " + password + " Screenshot " + screenshotFolder);
	  }
	  
	  @Test(dataProvider="userRegistrationData", dataProviderClass=LoginDataProviders.class)
	  public void UNFTD_003(String firstName, String lastName, String emailId, String telephone, String address1, String address2, String city, String postCode, String country, String region, String password, String confirmPassword) {		  
		  String screenshotFolder=System.getProperty("user.dir");
		  System.out.println("firstName = " + firstName + "\n lastName = " + lastName + "\n emailId= " + emailId + "\n telephone = " + telephone + "\n address1 = " + address1 + "\n address2 =" + address2 + "\n city = " + city + "\n postCode= " + postCode + "\n country = " + country + "\n region = " + region + "\n password =" + password + "\n confirmPassword=" + confirmPassword);
	  }*/
	  
	  @Test(dataProvider="user_account", dataProviderClass=LoginDataProviders.class)
	  public void dbDataProviderTest(String firstName, String lastName, String emailId, String telephoneNum, String faxNum, String company, String address1, String address2, String city, String postCode, String country, String region, String password, String confirmPassword, String subscribe) {
		  System.out.println(firstName + ", " + lastName + ", " + emailId);
	  }  
}
