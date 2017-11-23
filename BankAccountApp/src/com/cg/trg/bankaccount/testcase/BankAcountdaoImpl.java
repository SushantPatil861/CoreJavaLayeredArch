package com.cg.trg.bankaccount.testcase;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;
import com.cg.trg.bankaccount.service.BankAccountServiceImpl;
import com.cg.trg.bankaccount.service.IBankAccountService;

public class BankAcountdaoImpl {
	static  IBankAccountService service;
	Scanner sc = new Scanner(System.in);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new BankAccountServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testRaiseComplaint() {
		System.out.println("enter raiser name");
		String rais = sc.next();
		System.out.println("enter the related issue");
		String iss= sc.next();
		BankAccount obj = new BankAccount();
		String pri =service.setPriority(iss);
		String sta =service.setStatus(iss);
		obj.setRaiser(rais);
		obj.setPriority(pri);
		obj.setStatus(sta);
		assertEquals(1043,service.raiseComplaint(obj));
		//fail("Not yet implemented");
	}

	@Test
	public void testViewById() {
		System.out.println("enter complaint id to search");
		int  complaintId1 = sc.nextInt();
	BankAccount obj2 = new BankAccount();
		try {
					obj2=service.viewById(complaintId1);	
					if (obj2==null)
					{
					assertTrue(false);
					}
					else{
						System.out.print("raiser\t" + obj2.getRaiser()+ "\t");
						System.out.print("complaint id\t" + obj2.getComplaintId()+ "\t");
						System.out.print("status\t" + obj2.getStatus()+ "\t");
						System.out.print("priority\t" + obj2.getPriority()+ "\t");
						System.out.println();
						assertTrue(true);
				}
						
				}catch (BankAccountException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
}
}