package com.cg.trg.bankaccount.dao;

import com.cg.trg.bankaccount.dto.BankAccount;
import com.cg.trg.bankaccount.exception.BankAccountException;

public interface IBankAccoutDao {
int raiseComplaint(BankAccount obj);
BankAccount viewById(int complaintId)throws BankAccountException;

}
