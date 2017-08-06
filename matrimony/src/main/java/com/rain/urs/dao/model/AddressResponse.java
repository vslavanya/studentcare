package com.rain.urs.dao.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AddressResponse implements java.io.Serializable {

	private List<Address> address;
	
	public AddressResponse(){}

	@XmlElement
	public List<Address> getAddress() {
		return address;
	}

	
	public void setAddress(List<Address> addressList) {
		this.address = addressList;
	}

	@Override
	public String toString() {
		return "AddressResponse [addressList=" + address + "]";
	}

}
