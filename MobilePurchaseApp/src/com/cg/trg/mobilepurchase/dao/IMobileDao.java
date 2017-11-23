package com.cg.trg.mobilepurchase.dao;

import java.util.ArrayList;




import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;

public interface IMobileDao {
	     ArrayList<Mobile> showAllMobile() ; 
	     boolean deleteMobile(int mobileId);
	     ArrayList<Mobile> searchByPriceRange(float start ,float end) throws MobileException;
	     int purchaseMobile(Customer obj , String mobileName , int qty ) throws MobileException;

}
