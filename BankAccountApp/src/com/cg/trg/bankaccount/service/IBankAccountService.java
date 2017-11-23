package com.cg.trg.bankaccount.service;

import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;

public interface IBankAccountService {
int raiseComplaint(BankAccount obj);
public String setPriority(String issue);
public String setStatus(String issue);
BankAccount viewById(int complaintId) throws BankAccountException;
boolean validate(String Pattern,String input );

}
