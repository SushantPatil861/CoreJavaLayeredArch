package com.cg.trg.orderproduct1.pl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.cg.trg.orderproduct1.dto.Customer;
import com.cg.trg.orderproduct1.dto.Product;
import com.cg.trg.orderproduct1.exception.ProductException;
import com.cg.trg.orderproduct1.service.IProductService;
import com.cg.trg.orderproduct1.service.ProductServiceImpl;

public class Client {
	public static final String PHONE_PATTERN="[7/8/9][0-9]{9}";
	public static final String NAME_PATTERN="[A-Za-z]{5,15}";
	public static final String ID_PATTERN="[1-9][0-9]{3}";
	public static final String EMAILID_PATTERN="[a-z.-_]+[@][a-z]+.\\[a-z]{3}";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Order Product");
		int choice=-1;
		Scanner sc=new Scanner(System.in);
		IProductService service=new ProductServiceImpl();
		do
		{
			System.out.println("1.Search Product By Category");
			System.out.println("2.Order Product");
			System.out.println("3.Show All Products");
			System.out.println("4.Show Product By ID");
			System.out.println("0.Exit");
			System.out.println("Enter Choice");
			choice=sc.nextInt();
			
			switch(choice){
			case 0:
				System.out.println("Thank You");
				break;
			case 1:
				System.out.println("Enter Category");
				String category=sc.next();
				try {
					ArrayList<Product> prodList=service.srchProductByCategory(category);
					if(!prodList.isEmpty()){
						Iterator<Product> it=prodList.iterator();
						while(it.hasNext()){
							System.out.println(it.next());
						}
					}
					else{
						throw new ProductException("Invalid Category!!");
					}
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				Customer cust=new Customer();
				System.out.println("Enter Name");
				String name=sc.next();
				while(!service.validate(EMAILID_PATTERN,name)){
					System.out.println("Enter Valid Name");
					name=sc.next();
				}
				System.out.println("Enter Address");
				String address=sc.next();
				System.out.println("Enter PhoneNum");
				String phone=sc.next();
				while(!service.validate(PHONE_PATTERN,phone)){
					System.out.println("Phone number should start with [7/8/9] and must be 10 digit");
					phone=sc.next();
				}
				System.out.println("Enter ProductName");
				String prodName=sc.next();
				System.out.println("Enter Quantity");
				int qty=sc.nextInt();
				long phonenum=Long.parseLong(phone);
				cust.setCustName(name);
				cust.setCustAddress(address);
				cust.setCustPhone(phonenum);
				
				int orderId=service.orderProduct(cust, prodName, qty);
				if(orderId>0){
					System.out.println("Order Placed With Order ID::"+orderId);
				}
				else{
					System.out.println("Order Not Placed");
				}
				break;
			case 3:
				System.out.println("All Products::");
				try {
					ArrayList<Product> prodList=service.getAllProducts();
					if(!prodList.isEmpty()){
						Iterator<Product> it=prodList.iterator();
						while(it.hasNext()){
							System.out.println(it.next());
						}
					}
					else{
						throw new ProductException("Product Table Empty!!");
					}
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter productID");
				String pId=sc.next();
				while(!service.validate(ID_PATTERN,pId)){
					System.out.println("product must be 4 digit number");
					pId=sc.next();
				}
				int prodId=Integer.parseInt(pId);
				try {
					Product pdt=service.getProductbyId(prodId);
					if(pdt!=null){
							System.out.println(pdt);
					}else
					{
						throw new ProductException("Invalid prodID!!");
					}
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			}
		}while(choice!=0);
		sc.close();
	}

}
