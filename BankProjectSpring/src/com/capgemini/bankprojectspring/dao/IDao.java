package com.capgemini.bankprojectspring.dao;

import java.util.List; 

import com.capgemini.bankprojectspring.bean.CustomerDetail;
import com.capgemini.bankprojectspring.bean.Passbook;

public interface IDao {
	public boolean customerDetails(CustomerDetail cd);
	public CustomerDetail deposit(String mobno);
	public CustomerDetail withdraw(String mobno);
	public CustomerDetail showBalance(String mobno);
	public CustomerDetail fundTransfer(String mobno);
	public CustomerDetail displayDetails(String mobno);
	public List<Passbook> printTransactions(String mobno,int pin);
	public void update(CustomerDetail customer);
	public void transactions(String log,CustomerDetail cd);
}
