package com.cg.trg.orderproduct1.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;
import com.cg.trg.orderproduct1.service.IProductService;
import com.cg.trg.orderproduct1.service.ProductServiceImpl;

public class TestProductDAOImpl {

	static IProductService service;
	@BeforeClass
	public static void  setUp(){
		service=new ProductServiceImpl();
	}
	@AfterClass
	public static void tearDown(){
		service=null;
	}

	@Test
	public void testSrchProductByCategory() {
		System.out.println("Enter Category");
		Scanner sc=new Scanner(System.in);
		String cat=sc.next();
		//try catch block is used since srchProdBYCategory Method throws ProductException
		try{
			ArrayList<Product> prdList=service.srchProductByCategory(cat);
			if(prdList.isEmpty()){
				assertTrue(false);      //List is empty..No category Matched
			}
			else
			{
				for(Product pdt:prdList){
						System.out.println(pdt);
				}
				assertTrue(true);		//List is not empty	
			}
		}catch(ProductException e)
		{
			fail("Invalid Category "+e.getMessage());
		}
	}

	@Test
	public void testGetAllProducts() {
		ArrayList<Product> prdList=service.getAllProducts();
		if(prdList.isEmpty()){
			//list is empty
			assertTrue(false);
		}
		else{
			assertTrue(true);
			System.out.println("All products");
			for(Product pdt:prdList){
				System.out.println(pdt);
			}
		
		}
	}

	@Test
	public void testGetProductbyId() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product id to search");
		int prodId=sc.nextInt();
		try {
			Product p=service.getProductbyId(prodId);
			if(p==null){
				assertTrue(false);
			}
			else{
				assertTrue(true);
				System.out.println(p);
				
			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			fail("Invalid ID "+e.getMessage());
		}
		
	}

	@Test
	public void testOrderProduct() {
		Customer obj=new Customer();
		obj.setCustName("Alex");
		obj.setCustAddress("HearthRow");
		obj.setCustPhone(8552989056L);
		//nextval of oseq is 22..and may change in future
		assertEquals(22, service.orderProduct(obj,"Shirt",3));
	}
}
