package com.saas.edu.dto;

import java.util.Arrays;

public class SchoolDto extends AuditDto {
	
	@Override
	public String toString() {
		return "SchoolDto [schoolName=" + schoolName + ", address=" + address + ", city=" + city + ", district="
				+ district + ", state=" + state + ", pin=" + pin + ", post=" + post + ", addrType=" + addrType
				+ ", grades=" + Arrays.toString(grades) + ", schoolCode=" + schoolCode + "]";
	}
	private String schoolName;
	private String address;
	private String city;
	private String district;
	private String state;
	private String pin;
	private String post;
	private String addrType;
	private String[] grades;
	private String schoolCode;
	
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddrType() {
		return addrType;
	}
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	public String[] getGrades() {
		return grades;
	}
	public void setGrades(String[] grades) {
		this.grades = grades;
	}

}
