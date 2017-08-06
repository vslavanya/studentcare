package com.rain.urs.dao.model.request;

public class LookUpRequest {
	
	private String postOffice;
	private String pinCode;
	private LookUpType lookUpType;
	
	
	public String getPostOffice() {
		return postOffice;
	}


	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public LookUpType getLookUpType() {
		return lookUpType;
	}


	public void setLookUpType(LookUpType lookUpType) {
		this.lookUpType = lookUpType;
	}


	@Override
	public String toString() {
		return "LookUpRequest [postOffice=" + postOffice + ", pinCode=" + pinCode + ", lookUpType=" + lookUpType + "]";
	}
	

}
