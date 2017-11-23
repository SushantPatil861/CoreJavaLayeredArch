package com.cg.trg.mobilepurchase.service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.cg.trg.mobilepurchase.dao.MobileDAOImpl;
import com.cg.trg.mobilepurchase.dto.Customer;
import com.cg.trg.mobilepurchase.dto.Mobile;
import com.cg.trg.mobilepurchase.exception.MobileException;

public class MobileServiceImpl implements IMobileService {
	MobileDAOImpl dao = new MobileDAOImpl();

	@Override
	public ArrayList<Mobile> showAllMobile() {

		return dao.showAllMobile();
	}

	@Override
	public boolean deleteMobile(int mobileId) {

		return dao.deleteMobile(mobileId);
	}

	@Override
	public ArrayList<Mobile> searchByPriceRange(float start, float end) throws MobileException {

		return dao.searchByPriceRange(start, end);
	}

	@Override
	public int purchaseMobile(Customer obj, String mobileName, int qty) throws MobileException {

		return dao.purchaseMobile(obj, mobileName, qty);
	}

	@Override
	public boolean validate(String arg1, String arg2) {

		return Pattern.matches(arg1, arg2);
	}

}
