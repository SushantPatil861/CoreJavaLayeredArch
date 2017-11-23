package com.cg.trg.mobilepurchase.testcases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;
import com.cg.trg.mobilepurchase.service.IMobileService;
import com.cg.trg.mobilepurchase.service.MobileServiceImpl;

public class TestMobileDAOImpl {
	static IMobileService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new MobileServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testShowAllMobile() {
		ArrayList<Mobile> mobileList = new ArrayList<Mobile>();
		try {
			mobileList = service.showAllMobile();
			if (mobileList.isEmpty()) {
				assertTrue(false);
			} else {
				assertTrue(true);
			}
		} catch (Exception e) {
			fail("DB empty" + e.getMessage());
		}
	}

	@Test
	public void testSearchByPriceRange() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		ArrayList<Mobile> prdList = new ArrayList<Mobile>();
		try {
			System.out.println("Enter start price");
			float start = Float.parseFloat(br.readLine());
			System.out.println("Enter end price");
			float end = Float.parseFloat(br.readLine());
			try {
				prdList = service.searchByPriceRange(start, end);
				if (prdList.isEmpty()) {
					assertTrue(false);
				} else {
					assertTrue(true);
				}

			} catch (MobileException e) {
				fail("Price out of range" + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Test
	public void testPurchaseMobile() {
		int purchaseId = 0;
		Customer obj = new Customer();
		obj.setCustName("RAJAT");
		obj.setMailId("rajat@gmail.com");
		obj.setPhoneNo(7350780413L);
		try {
			purchaseId = service.purchaseMobile(obj, "Sony Xperia C", 2);
			assertEquals(3, purchaseId);
		} catch (MobileException e) {
			fail(e.getMessage());
		}

	}
}