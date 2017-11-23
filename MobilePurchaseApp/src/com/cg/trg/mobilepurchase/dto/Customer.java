package com.cg.trg.mobilepurchase.dto;

public class Customer {
private String custName;
private String mailId ;
private long phoneNo ;

public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getMailId() {
	return mailId;
}
public void setMailId(String mailId) {
	this.mailId = mailId;
}
public long getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(long phoneNo) {
	this.phoneNo = phoneNo;
}
@Override
public String toString() {
	return "Customer [custName=" + custName + ", mailId=" + mailId
			+ ", phoneNo=" + phoneNo + "]";
}



}
