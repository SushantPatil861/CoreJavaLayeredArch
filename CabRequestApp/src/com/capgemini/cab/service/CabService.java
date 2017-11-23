package com.capgemini.cab.service;

import java.util.regex.Pattern;

import com.capgemini.cab.dao.CabRequestDAO;
import com.capgemini.cab.dao.ICabRequestDAO;
import com.capgemini.cab.exception.CabException;
import com.capgemini.cabs.bean.CabRequest;



public class CabService implements ICabService{

	ICabRequestDAO dao;
	public  CabService() {
	
		dao = new CabRequestDAO();
	}

	
	
	@Override
	public boolean validate(String pattern, String input) {
		// TODO Auto-generated method stub
		return Pattern.matches(pattern, input);
	}



	@Override
	public int addCabRequestDetails(CabRequest obj, String pinCode) throws CabException {
		// TODO Auto-generated method stub
		
		String requestStatus="Not Accepted";
		String cabNumber = "null";
			if(pinCode.equals("400096") ){
				requestStatus = "Accepted";
				cabNumber = "MH VS 2345";
			}else if(pinCode.equals("411026")){
				requestStatus = "Accepted";
				cabNumber = "MH VH 34567";
			}
			else if(pinCode.equals("411013")){
				requestStatus = "Accepted";
				cabNumber = "MH AN 97886";
			}
			else if(pinCode.equals("560066")){
				requestStatus = "Accepted";
				cabNumber = "MH AS 875";
			}
			else if(pinCode.equals("382009")){
				requestStatus = "Accepted";
				cabNumber = "MH KN 9856";
			}
			else if(pinCode.equals("400708")){
				requestStatus = "Accepted";
				cabNumber = "MH TF 8956";
			}
			
			obj.setRequestStatus(requestStatus);
			obj.setCabNumber(cabNumber);
			obj.setPinCode(pinCode);
			return dao.addCabRequestDetails(obj, pinCode);
		

	}


	@Override
	public CabRequest getRequestDetails(int requestId) throws CabException {
		// TODO Auto-generated method stub
		return dao.getRequestDetails(requestId);
	}

}
