package com.cg.trg.bankaccount.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;
import com.cg.trg.bankaccount.service.BankAccountServiceImpl;
import com.cg.trg.bankaccount.service.IBankAccountService;

public class Client {
	public static final String NAME_PATTERN = "[A-Z]{1}[a-z]{4,15}";
	public static final String ID_PATTERN = "[1-9][0-9]{3}";

	public static void main(String[] args) {
		System.out.println("welcome to bank complaint sys");
		int choice = -1;
		IBankAccountService service = new BankAccountServiceImpl();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			do {
				System.out.println("1. Raise Complaints ");
				System.out.println("2. View Complaints");

				System.out.println("0.exit");
				System.out.println("enter choice");
				String ch = br.readLine();
				choice = Integer.parseInt(ch);
				switch (choice) {
				case 1:
					System.out.println("enter raiser name");
					String name = br.readLine();
					while (!service.validate(NAME_PATTERN, name)) {
						System.out.println("invalid name");
						name = br.readLine();
					}
					System.out.println("enter the related issue");
					String issue = br.readLine();
					String pri = service.setPriority(issue);
					String sta = service.setStatus(issue);
					BankAccount obj = new BankAccount();
					obj.setRaiser(name);
					obj.setPriority(pri);
					obj.setStatus(sta);

					int complaintId = service.raiseComplaint(obj);
					System.out.println("complaint raised " + complaintId);

					break;

				case 2:
					System.out.println("enter complaint id to search");
					String complaintId1 = br.readLine();
					while (!service.validate(ID_PATTERN, complaintId1)) {
						System.out.println("prod id is 4 digit number");
						complaintId1 = br.readLine();
					}
					int complaintId2 = Integer.parseInt(complaintId1);
					BankAccount obj2 = new BankAccount();
					try {
						obj2 = service.viewById(complaintId2);
						System.out.print("raiser\t" + obj2.getRaiser() + "\t");
						System.out.print("complaint id\t"
								+ obj2.getComplaintId() + "\t");
						System.out.print("status\t" + obj2.getStatus() + "\t");
						System.out.print("priority\t" + obj2.getPriority()
								+ "\t");
						System.out.println();
					} catch (BankAccountException e) {
						System.out.println(e.getMessage());
						// e.printStackTrace();
					}

					break;
				case 0:
					break;

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
