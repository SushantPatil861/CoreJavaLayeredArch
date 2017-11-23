package com.capgemini.cab.dao;

import com.capgemini.cab.exception.CabException;
import com.capgemini.cabs.bean.CabRequest;

public interface ICabRequestDAO {

	int addCabRequestDetails(CabRequest cabRequest,String pinCode) throws CabException;
	
	CabRequest getRequestDetails(int requestId) throws CabException;
}
