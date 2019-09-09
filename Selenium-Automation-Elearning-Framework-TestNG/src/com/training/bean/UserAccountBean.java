package com.training.bean;

public class UserAccountBean {
	private String firstName;
	private String lastName;
	private String emailId;
	private String telephoneNum;
	private String faxNum;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String postCode;
	private String country;
	private String region;
	private String password;
	private String confirmPassword;
	private String subscribe;

	public UserAccountBean() {
	}

	public UserAccountBean(String firstName, String lastName, String emailId, String telephoneNum, String faxNum, String company, String address1, String address2, String city, String postCode, String country, String region, String password, String confirmPassword, String subscribe) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.emailId=emailId;
		this.telephoneNum=telephoneNum;
		this.faxNum=faxNum;
		this.company=company;
		this.address1=address1;
		this.address2=address2;
		this.city=city;
		this.postCode=postCode;
		this.country=country;
		this.region=region;
		this.password=password;
		this.confirmPassword=confirmPassword;
		this.subscribe=subscribe;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	@Override
	public String toString() {
		return "LoginBean [userName=" + firstName + ", password=" + lastName + "]";
	}

}
