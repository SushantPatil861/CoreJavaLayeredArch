package com.cg.trg.mobilepurchase.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;
import com.cg.trg.mobilepurchase.service.MobileServiceImpl;

public class Client {
	public static final String NAME_PATTERN = "[A-Z][a-z]{3,}";
	public static final String PHONE_PATTERN = "[7-9][0-9]{9}";
	public static final String MAIL_PATTERN = "[a-z.-_]+[@]+[a-z]+.\\[a-z]{3}";
	public static final String ID_PATTERN = "[1][0-9]{3}";

	public static void main(String[] args) throws MobileException {
		MobileServiceImpl service = new MobileServiceImpl();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int choice = 0;
		try {

			do {

				System.out.println("Choose an Option");
				System.out.println("1.Show All Mobiles");
				System.out.println("2.Search Mobile By Price");
				System.out.println("3.Place Order");
				System.out.println("4.Delete Mobile");
				System.out.println("0.Exit");
				choice = Integer.parseInt(br.readLine());

				switch (choice) {

				case 1: {
					System.out.println("hi");
					ArrayList<Mobile> mobileList= null ;
					try {
						mobileList = service.showAllMobile();
						for (Mobile obj : mobileList) {
							System.out.println(obj);
							
						}

					} catch (Exception e) {

						System.out.println(e.getMessage());
						throw new MobileException("cfgjnf");
					}
					break;
				}

				case 2: {
					System.out.println("Enter Starting Point");
					float start = Float.parseFloat(br.readLine());
					System.out.println("Enter Ending Point");
					float end = Float.parseFloat(br.readLine());
					ArrayList<Mobile> mobileList = new ArrayList<Mobile>();
					try {
						mobileList = service.searchByPriceRange(start, end);
						for (Mobile obj : mobileList) {
							System.out.println(obj);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new MobileException("cfgjnf");
					}
					break;

				}

				case 3: {
					System.out.println("Enter Name::");
					String custName = br.readLine();
					while (!service.validate(NAME_PATTERN, custName)) {
						System.out
								.println("Invalid Name .... Must Start with capital letter");
						custName = br.readLine();
					}
					System.out.println("Enter mail Id");
					String mailId = br.readLine();
				/*	while (!service.validate(MAIL_PATTERN, mailId)) {
						System.out
								.println("Invalid Mail ID ... Must be othe format :: abc@gmail.com");
						mailId = br.readLine();
					}
*/					System.out.println("Enter phone number :");
					String phone = br.readLine();
					while (!service.validate(PHONE_PATTERN, phone)) {
						System.out
								.println("Invalid Phone Number.. It should start with 7 ,8 or 9 ");
						phone = br.readLine();

					}
					long phoneNo = Long.valueOf(phone);
					Customer obj = new Customer();
					obj.setCustName(custName);
					obj.setMailId(mailId);
					obj.setPhoneNo(phoneNo);
					System.out.println("Enter Mobile bought");
					String mobileName = br.readLine();
					System.out.println("Enter quantity purchased");
					int qty = Integer.parseInt(br.readLine());
					try {
						int purchaseId = service.purchaseMobile(obj,
								mobileName, qty);
						if (purchaseId > 0) {
							System.out
									.println("Purchase Done with purchaseId :"
											+ purchaseId);
						} else {
							if (purchaseId == -1) {
								System.out
										.println("Not enough units left to buy");
							} else {
								System.out.println("Purchase Failed");
							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new MobileException("cfgjnf");
					}
					break;
				}

				case 4: {
					boolean status = false;
					System.out.println("Enter mobileId to be deleted");
					String mId = br.readLine();
					while (!service.validate(ID_PATTERN, mId)) {
						System.out.println("Invalid mobileId ");
						mId = br.readLine();
					}
					int mobileId = Integer.valueOf(mId);
					try {
						status = service.deleteMobile(mobileId);
						if (status) {
							System.out.println("Deletion Succesful");
						} else {
							System.out.println("Deletion Failed");
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new MobileException("cfgjnf");
					}
					break;
				}
				case 0: {
					System.out.println("Exiting");
					System.exit(0);
				}
				default:
					System.out.println("Invalid Entry");
				}
			} while (choice != 0);

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

}
