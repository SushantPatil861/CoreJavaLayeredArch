package com.cg.trg.orderproduct1.dto;

public class Customer {
	private String custName;
	private String custAddress;
	private long custPhone;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public long getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(long custPhone) {
		this.custPhone = custPhone;
	}
	@Override
	public String toString() {
		return "Customer [custName=" + custName + ", custAddress="
				+ custAddress + ", custPhone=" + custPhone + "]";
	}
	
}
