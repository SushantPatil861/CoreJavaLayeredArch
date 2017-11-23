package com.cg.trg.orderproduct1.dao;

import java.util.ArrayList;

import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;

public interface IProductDAO {
	ArrayList<Product> srchProductByCategory(String category) throws ProductException;
	ArrayList<Product> getAllProducts();
	Product getProductbyId(int prodId) throws ProductException;
	int orderProduct(Customer obj,String prodName,int qty); //returning order id which will be generated via sequence
}
