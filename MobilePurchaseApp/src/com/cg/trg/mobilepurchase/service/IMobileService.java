package com.cg.trg.mobilepurchase.service;

import java.util.ArrayList;

import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;

public interface IMobileService {

			ArrayList<Mobile> showAllMobile() ; 
	     boolean deleteMobile(int mobileId);
	     ArrayList<Mobile> searchByPriceRange(float start ,float end) throws MobileException;
	     int purchaseMobile(Customer obj , String mobileName , int qty ) throws MobileException;

boolean validate(String arg1 , String arg2);
}