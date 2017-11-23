package com.cg.trg.orderproduct1.service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.cg.trg.orderproduct1.dao.IProductDAO;
import com.cg.trg.orderproduct1.dao.ProductDAOImpl;
import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;

public class ProductServiceImpl implements IProductService{
	IProductDAO dao;
	public ProductServiceImpl() {
	// TODO Auto-generated constructor stub
		dao=new ProductDAOImpl();
	}
	@Override
	public ArrayList<Product> srchProductByCategory(String category)
			throws ProductException {
		// TODO Auto-generated method stub
		return dao.srchProductByCategory(category);
	}
	@Override
	public ArrayList<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return dao.getAllProducts();
	}
	@Override
	public Product getProductbyId(int prodId) throws ProductException {
		// TODO Auto-generated method stub
		return dao.getProductbyId(prodId);
	}
	@Override
	public int orderProduct(Customer obj, String prodName, int qty) {
		// TODO Auto-generated method stub
		return dao.orderProduct(obj, prodName, qty);
	}
	@Override
	public boolean validate(String pattern, String input) {
		// TODO Auto-generated method stub
		return Pattern.matches(pattern,input);
	}

}
