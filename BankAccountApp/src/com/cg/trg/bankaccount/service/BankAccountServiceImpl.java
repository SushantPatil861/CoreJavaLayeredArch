package com.cg.trg.bankaccount.service;

import java.util.regex.Pattern;

import com.cg.trg.bankaccount.dao.BankAcountDaoImpl;
import com.cg.trg.bankaccount.dao.IBankAccoutDao;
import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;

public class BankAccountServiceImpl implements IBankAccountService{
			IBankAccoutDao dao;
	public BankAccountServiceImpl(){
		dao = new BankAcountDaoImpl();
	}
	@Override
	public int raiseComplaint(BankAccount obj) {
	return dao.raiseComplaint(obj);

	}
	@Override
	public String setPriority(String issue) {
		String pr = null;
	if (issue.equals("Internet Banking")){
			pr = "High";
	}
	else if(issue.equals("Loan")){
		pr = "Medium";
	}
	else if(issue.equals("Cheque")){
		pr = "Low";
	}
	else if(issue.equals("Other")){
		pr = "Not Set";
	}
		return pr; 
	}
	@Override
	public String setStatus(String issue) {
				String st = null;
				if(issue.equals("Internet Banking")){
					st = "Open";
				}
				else if(issue.equals("Loan")){
					st = "Open";
				}
				else if(issue.equals("Cheque")){
					st = "Open";
				}
				else if(issue.equals("Others")){
					st = "";
				}
		return st;
	}
	@Override
	public BankAccount viewById(int complaintId) throws BankAccountException {
		return dao.viewById(complaintId);
	}
	@Override
	public boolean validate(String pattern, String input) {
		// TODO Auto-generated method stub
		return  Pattern.matches(pattern, input);
	}

}
