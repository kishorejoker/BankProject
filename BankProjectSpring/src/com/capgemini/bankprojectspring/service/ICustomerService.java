package com.capgemini.bankprojectspring.service;

import java.util.List;

import com.capgemini.bankprojectspring.bean.CustomerDetail;
import com.capgemini.bankprojectspring.bean.Passbook;

public interface ICustomerService {
	public String addCustomer(CustomerDetail cd);
	public String deposit(String mobno,double amt,int pin);
	public String withdraw(String mobno,double amt,int pin);
	public CustomerDetail showBalance(String mobno,int pin);
	public String fundTransfer(String rmobno, String smobno, double amt,int pin);
	public List<Passbook> printTransactions(String mobno,int pin);
	public CustomerDetail displayDetails(String mobno,int pin);

}
