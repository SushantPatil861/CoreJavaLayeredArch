package com.cg.trg.mobilepurchase.dto;

public class Mobile {
private int mobileId ;
private String mobileName ;
private float price ;
private int qty ;

public int getMobileId() {
	return mobileId;
}
public void setMobileId(int mobileId) {
	this.mobileId = mobileId;
}
public String getMobileName() {
	return mobileName;
}
public void setMobileName(String mobileName) {
	this.mobileName = mobileName;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
@Override
public String toString() {
	return "Mobile [mobileId=" + mobileId + ", mobileName=" + mobileName
			+ ", price=" + price + ", qty=" + qty + "]";
}




}
