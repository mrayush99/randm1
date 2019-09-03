package com.training.dataproviders;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

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
		return new ApachePOIExcelRead().getExcelContent(fileName); 
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
		for(int i=0; i<loginData.length; i++) {
			System.out.println(loginData[i][0] + " " + loginData[i][1] + " " + loginData[i][2]);
		}
		return loginData; 
	}

	@DataProvider(name = "userRegistrationData")
	public Object[][] getRegistrationData(Method testMethod){
		// ensure you will have the title as first line in the file 
		String testcaseId;
		String [][] loginData=null;
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\SeleniumProjctes\\final-framework-testng\\resources\\UniformStoreTestSuite.xlsx"; 
		String [][] fullData = new ApachePOIExcelRead().getExcelContent(fileName, "LoginSuite");
		int userCol=2;
		int passCol=3;
		int newPassCol=4;
		int numTests = 0;
		int j=0;
		for (int numRows=0; numRows< fullData.length; numRows++) {
			if(fullData[numRows][0].equals(testMethod.getName().toString())) {
				numTests++;	
			}
		}
		loginData=new String[numTests][3];
		for (int i=0; i< fullData[0].length; i++) {
			testcaseId=fullData[i][0].toString();
			if(testcaseId.equals(testMethod.getName().toString())) {
				loginData[j][0]=fullData[i][userCol];
				loginData[j][1]=fullData[i][passCol];
				loginData[j][2]=fullData[i][newPassCol];
				j++;
			}
		}
		return loginData; 
	}	
	
	@DataProvider(name = "login")
	public Object[][] getLoginData(Method testMethod){
		// ensure you will have the title as first line in the file 
		String [] fields = {"USER_NAME", "PASSWORD", "COMMENT"};
		String fileName ="C:\\Users\\VINODHFRANCIS\\git\\SeleniumProjctes\\final-framework-testng\\resources\\UniformStoreTestSuite.xlsx";
		String [][] fullData = new ApachePOIExcelRead().getExcelContent(fileName, "LoginSuite");
		String [][] loginData = new ApachePOIExcelRead().getColumnData(fullData, fields, testMethod.getName());
		for(int i=0; i<loginData.length; i++) {
			System.out.println(loginData[i][0] + " " + loginData[i][1] + " " + loginData[i][2]);
		}
		return loginData; 
	}		
}
