package com.capgemini.cabs.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.capgemini.cab.exception.CabException;
import com.capgemini.cab.service.CabService;
import com.capgemini.cab.service.ICabService;
import com.capgemini.cabs.bean.CabRequest;



public class Client {
	
	public static final String PHONE_PATTERN="[7-9][0-9]{9}";
	public static final String NAME_PATTERN="[[A-Za-z]*[\\s]*]{4,15}[\\s]*"; //NAME_PATTERN="[A-Za-z]{5,15}";
	public static final String ID_PATTERN="[1-9][0-9]{3}";
	public static final String PIN_PATTERN="[3-5][0-9]{5}";


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to Car Rental Application");
		int choice = -1;
		ICabService service = new CabService();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try{
			do{
				System.out.println("\n1.Raise Cab Request ");
				System.out.println("2. View Cab Request Status");
				System.out.println("3. Exit");
				
				System.out.println("Enter Choice");
				choice = Integer.parseInt(br.readLine());
				System.out.println(choice);
				//client -> Service
				switch(choice){
				
					case 1:		
						System.out.println("Enter the name of the customer");
						String custname = br.readLine();
						while(!service.validate(NAME_PATTERN, custname)){
							System.out.println("Enter alphabets only should be minimum 4 and max 15 characters");
							custname = br.readLine();
						}
						
						System.out.println("Enter customer phone number");
						String phoneNumber = br.readLine();
						while(!service.validate(PHONE_PATTERN, phoneNumber)){
							System.out.println("Inalid phone (should start with 7/8/9 ..10 digits)");
							phoneNumber = br.readLine();
						}
						
						System.out.println("Enter Pick up address");
						String address = br.readLine();
						
						System.out.println("Enter Pin Code");
						String pinCode = br.readLine();
						while(!service.validate(PIN_PATTERN, pinCode)){
							System.out.println("Inalid pin Code (should start with 3/4/5..6 digits)");
							pinCode = br.readLine();
						}
						
						
						CabRequest obj = new CabRequest();
						
						obj.setCustomerName(custname);
						obj.setPhoneNumber(phoneNumber);
						obj.setAddress(address);
						
						try {
							int requestId = service.addCabRequestDetails(obj, pinCode);
							
							if(requestId !=0){
								System.out.println("Your Cab Request has been successfully registered,your request ID is :<"+requestId+">");
							}else{
								throw new CabException("Your Request for Cab was not Placed!!!");
							}
						} catch (CabException e) {
							System.out.println(e.getMessage());
						}
								break;
					case 2:
						System.out.println("Enter the requestID");
						String rId = br.readLine();
						
						while(!service.validate(ID_PATTERN, rId)){
							System.out.println("Inalid pin Code (should start with 3/4/5..6 digits)");
							rId = br.readLine();
						}	
						int requestId = Integer.parseInt(rId);
							try {
								
						
								CabRequest obj2 = service.getRequestDetails(requestId);
								
								if(obj2!=null){
									System.out.println("Name of the Customer\t"+ obj2.getCustomerName());
									System.out.println("Request Status\t" + obj2.getRequestStatus());
									System.out.println("Cab number\t"+ obj2.getCabNumber());
									System.out.println("Pickup Address\t"+ obj2.getAddress());
								
								}else{
									throw new CabException("Invliad requestId entered");
								}
							} catch (CabException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								System.out.println(e.getMessage());
							}
						
						break;
					 
			
					case 3:
						System.out.println("Program terminated");
						System.exit(0);
			
				}
		
			}while(choice !=0 );
		}catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
			
		}

	}


