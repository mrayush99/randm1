package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public List<UserAccountBean> getUserAccount(String user){
		String sql = properties.getProperty("get.user_account1"); 
		
		GetConnection gc  = new GetConnection(); 
		List<UserAccountBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			gc.ps1.setString(1,  user);
			
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
	public boolean addUserAccount(HashMap<String,String> userAccount) throws SQLException{
		String sql = properties.getProperty("add.user_account"); 
		boolean status=false;
		Connection con = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails());
		PreparedStatement stmt=con.prepareStatement(sql);  
		try {
			stmt.setString(1, userAccount.get("firstName"));
			stmt.setString(2, userAccount.get("lastName"));
			stmt.setString(3, userAccount.get("user"));
			stmt.setString(4, userAccount.get("phoneNumber"));
			stmt.setString(5, userAccount.get("faxNumber"));
			stmt.setString(6, userAccount.get("companyName"));
			stmt.setString(7, userAccount.get("address1"));
			stmt.setString(8, userAccount.get("address2"));
			stmt.setString(9, userAccount.get("cityName"));
			stmt.setString(10, userAccount.get("postCode"));
			stmt.setString(11, userAccount.get("countryName"));
			stmt.setString(12, userAccount.get("region"));
			stmt.setString(13, userAccount.get("password"));
			stmt.setString(14, userAccount.get("cPassword"));
			stmt.setString(15, userAccount.get("subscribe"));
			
			System.out.println(stmt);
				
			stmt.execute(); 
				
			status=true;
		}catch (SQLException e) {
			status=false;
			e.printStackTrace();
		}
		return status;
	}
	
	public static void main(String[] args) throws SQLException {
		new ELearningDAO().getLogins().forEach(System.out :: println);
		
	/*	String user="email2000@gmail.com";
		String firstName="First2000";
		String lastName="Last2000";		
		String password="Pass2000";	
		String cPassword="Pass2000";	
		String phoneNumber="9890989754";	
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
		hm.put("subscribe", subscribe);*/
		
		ELearningDAO ed= new ELearningDAO();
		//boolean status=ed.addUserAccount(hm);
		List<UserAccountBean> status=ed.getUserAccount("email2000@gmail.com");
		for ( UserAccountBean ub : status) {
			System.out.println(ub.getAddress1());
		}
	}
}
