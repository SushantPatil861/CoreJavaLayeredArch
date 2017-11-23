package com.capgemini.cab.service;

import com.capgemini.cab.exception.CabException;
import com.capgemini.cabs.bean.CabRequest;


public interface ICabService {
	
	int addCabRequestDetails(CabRequest cabRequest,String pinCode) throws CabException;
	
	CabRequest getRequestDetails(int requestId) throws CabException;
	
	boolean validate(String pattern,String input);
}
