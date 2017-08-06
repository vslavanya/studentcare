package com.saas.edu.dto;

public class StudentDto {
	
	private String rollNum;
	
	private String firstName;
	private String lastName;
	private String gender;
	private String parentFirstName;
	private String parentLastName;
	private String parentAddress;
	private String parentPhoneNum;
	private String parentEmailNum;
	private String relationtoParent;
	
	
	public String getRollNum() {
		return rollNum;
	}
	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getParentFirstName() {
		return parentFirstName;
	}
	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}
	public String getParentLastName() {
		return parentLastName;
	}
	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}
	public String getParentAddress() {
		return parentAddress;
	}
	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}
	public String getParentPhoneNum() {
		return parentPhoneNum;
	}
	public void setParentPhoneNum(String parentPhoneNum) {
		this.parentPhoneNum = parentPhoneNum;
	}
	public String getParentEmailNum() {
		return parentEmailNum;
	}
	public void setParentEmailNum(String parentEmailNum) {
		this.parentEmailNum = parentEmailNum;
	}
	public String getRelationtoParent() {
		return relationtoParent;
	}
	public void setRelationtoParent(String relationtoParent) {
		this.relationtoParent = relationtoParent;
	}
	
	@Override
	public String toString() {
		return "StudentDto [rollNum=" + rollNum + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", parentFirstName=" + parentFirstName + ", parentLastName=" + parentLastName
				+ ", parentAddress=" + parentAddress + ", parentPhoneNum=" + parentPhoneNum + ", parentEmailNum="
				+ parentEmailNum + ", relationtoParent=" + relationtoParent + "]";
	}
	
	
	
}
