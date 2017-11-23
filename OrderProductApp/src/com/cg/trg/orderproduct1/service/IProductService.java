package com.cg.trg.orderproduct1.service;

import java.util.ArrayList;

import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;

public interface IProductService {
	ArrayList<Product> srchProductByCategory(String category) throws ProductException;
	ArrayList<Product> getAllProducts();
	Product getProductbyId(int prodId) throws ProductException;
	int orderProduct(Customer obj,String prodName,int qty); //returning order id which will be generated via sequence

	boolean validate(String pattern,String input);    //used for validation of input fields
}
