package com.cg.trg.orderproduct1.dto;

public class Product {
	private int prodId;
	private String prodName;
	private float prodPrice;
	private int prodQty;
	private String category;
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName
				+ ", prodPrice=" + prodPrice + ", prodQty=" + prodQty
				+ ", category=" + category + "]";
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public float getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(float prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdQty() {
		return prodQty;
	}
	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
