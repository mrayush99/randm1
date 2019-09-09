package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.LoginBean;
import com.training.bean.UserAccountBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class ELearningDAO {
	
	Properties properties; 
	
	public ELearningDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<LoginBean> getLogins(){
		String sql = properties.getProperty("get.logins"); 
		
		GetConnection gc  = new GetConnection(); 
		List<LoginBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<LoginBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				LoginBean temp = new LoginBean(); 
				temp.setUserName(gc.rs1.getString(1));
				temp.setPassword(gc.rs1.getString(2));

				list.add(temp); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public List<UserAccountBean> getUserAccount(){
		String sql = properties.getProperty("get.user_account"); 
		
		GetConnection gc  = new GetConnection(); 
		List<UserAccountBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<UserAccountBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				UserAccountBean temp = new UserAccountBean(); 
				temp.setFirstName(gc.rs1.getString(1));
				temp.setLastName(gc.rs1.getString(2));
				temp.setEmailId(gc.rs1.getString(3));
				temp.setTelephoneNum(gc.rs1.getString(4));
				temp.setFaxNum(gc.rs1.getString(5));
				temp.setCompany(gc.rs1.getString(6));
				temp.setAddress1(gc.rs1.getString(7));
				temp.setAddress2(gc.rs1.getString(8));
				temp.setCity(gc.rs1.getString(9));
				temp.setPostCode(gc.rs1.getString(10));
				temp.setCountry(gc.rs1.getString(11));
				temp.setRegion(gc.rs1.getString(12));
				temp.setPassword(gc.rs1.getString(13));
				temp.setConfirmPassword(gc.rs1.getString(14));
				temp.setSubscribe(gc.rs1.getString(15));

				list.add(temp); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}	
	
	public static void main(String[] args) {
		new ELearningDAO().getLogins().forEach(System.out :: println);
	}
	
	
}
