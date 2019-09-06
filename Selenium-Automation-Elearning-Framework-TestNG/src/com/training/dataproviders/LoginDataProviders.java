package com.training.dataproviders;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public static Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public String[][] getExcelData(){
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\SeleniumProjctes\\final-framework-testng\\resources\\UniformStoreTestSuite.xlsx";
		String sheetName="Sheet1";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}	
	
	@DataProvider(name = "changePassword")
	public Object[][] getChangePasswordData(Method testMethod){
		// ensure you will have the title as first line in the file 
		// ensure you will have the title as first line in the file 
		String [] fields = {"USER_NAME", "PASSWORD", "NEW_PASSWORD", "COMMENT"};
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\SeleniumProjctes\\final-framework-testng\\resources\\UniformStoreTestSuite.xlsx";
		String [][] fullData = new ApachePOIExcelRead().getExcelContent(fileName, "LoginSuite");
		String [][] loginData = new ApachePOIExcelRead().getColumnData(fullData, fields, testMethod.getName());
		return loginData; 
	}

	@SuppressWarnings("null")
	@DataProvider(name = "userRegistrationData")
	public static Object[][] getRegistrationData(Method testMethod){
		// ensure you will have the title as first line in the file 
		String [] fields = {"First Name", "Last Name", "E-Mail", "Telephone", "Address 1", "Address 2", "City", "Postal Code", "Country", "Region", "Password", "Password Confirm"};
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\Selenium\\Selenium-Automation-Elearning-Framework-TestNG\\resources\\UniformStoreTestSuite.xlsx";
		String [][] fullData = new ApachePOIExcelRead().getExcelContent(fileName, "RegistrationSuite");
		String [][] registrationData = new ApachePOIExcelRead().getColumnData(fullData, fields, testMethod.getName());
		return registrationData;
	}	
	
	@DataProvider(name = "login")
	public static Object[][] getLoginData(Method testMethod){
		// ensure you will have the title as first line in the file 
		System.out.println("Entered into login DP");
		String [] fields = {"USER_NAME", "PASSWORD"};
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\Selenium\\Selenium-Automation-Elearning-Framework-TestNG\\resources\\UniformStoreTestSuite.xlsx";
		String [][] fullData = new ApachePOIExcelRead().getExcelContent(fileName, "LoginSuite");
		String [][] loginData = new ApachePOIExcelRead().getColumnData(fullData, fields, testMethod.getName());
		for(int i=0; i<loginData.length; i++) {
			System.out.println(loginData[i][0] + " " + loginData[i][1] );
		}
		return loginData; 
	}		
}
