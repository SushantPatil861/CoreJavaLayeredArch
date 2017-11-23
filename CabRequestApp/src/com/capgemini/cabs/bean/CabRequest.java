package com.capgemini.cabs.bean;

import java.time.LocalDate;

public class CabRequest {
	
	private int requestId;
	private String customerName;
	private String phoneNumber;
	private LocalDate date;
	private String requestStatus;
	private String cabNumber;
	private String address;
	private String pinCode;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getCabNumber() {
		return cabNumber;
	}
	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	@Override
	public String toString() {
		return "cab [requestId=" + requestId + ", customerName=" + customerName
				+ ", phoneNumber=" + phoneNumber + ", date=" + date
				+ ", requestStatus=" + requestStatus + ", cabNumber="
				+ cabNumber + ", address=" + address + ", pinCode=" + pinCode
				+ "]";
	}
	
	


}
