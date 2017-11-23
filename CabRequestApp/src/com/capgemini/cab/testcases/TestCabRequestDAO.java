package com.capgemini.cab.testcases;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.cab.exception.CabException;
import com.capgemini.cab.service.CabService;
import com.capgemini.cab.service.ICabService;
import com.capgemini.cabs.bean.CabRequest;



public class TestCabRequestDAO {

	static ICabService service;
	Scanner sc =new Scanner(System.in);
	
	@BeforeClass
	public static void setUp(){
		service = new CabService();
		
	}
	
	@AfterClass
	public  static void tearDown(){
		service = null;
	}
	
	@Test
	public void testAddCabRequestDetails() {
		
		CabRequest obj = new CabRequest();
		
		obj.setCustomerName("Primus");
		obj.setPhoneNumber("9890992451");
		obj.setAddress("Pradhikaran , Pune");
		//Here check the expected seq_request_id value from dual
		//select oseq.nextval from dual;
		// accordingly set the expected value in asertEquals

			try {
				assertEquals(1010, service.addCabRequestDetails(obj, "401207"));
			} catch (CabException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fail("Request not Placed!!" + e.getMessage());
			}
		
	}

	@Test
	public void testGetRequestDetails() {
		
		System.out.println("Enter request Id to be Searched");
		int requestId = sc.nextInt();
		try{
			CabRequest obj2 = service.getRequestDetails(requestId);
			
			if(obj2 == null){
					//no category matched list is empty
				assertTrue(false);
			}else{
				System.out.println("Name of the Customer\t"+ obj2.getCustomerName());
				System.out.println("Request Status\t" + obj2.getRequestStatus());
				System.out.println("Cab number\t"+ obj2.getCabNumber());
				System.out.println("Pickup Address\t"+ obj2.getAddress());
			}
		} catch (CabException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			fail("Invalid Request ID!!" + e.getMessage());
			}	
	}
}
	
	
