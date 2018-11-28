package com.capgemini.bankprojectspring.service;

import java.util.Date; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankprojectspring.bean.CustomerDetail;
import com.capgemini.bankprojectspring.bean.Passbook;
import com.capgemini.bankprojectspring.dao.IDao;
import com.capgemini.bankprojectspring.exception.InvalidInputException;



@Service
public  class ImplCustomerService implements ICustomerService {
	@Autowired
	IDao dao;
	double bal; 

	@Override
	public String addCustomer(CustomerDetail cd)throws InvalidInputException{
		dao.customerDetails(cd);
		return "Account created!!!";
	}

	@Override
	public String deposit(String mobno,double amount,int pin)throws InvalidInputException {
			CustomerDetail cd = dao.deposit(mobno);
			double balance = cd.getBalance();
			if(isValidamt(amount)&&cd.getPin()==pin){
				balance=amount+balance;
			}else{
				throw new InvalidInputException(" : Amount or PIN is Invalid!");
			}
			cd.setBalance(balance);
			String depositlog = new Date()+","+amount+" is credited to your Account Linked MobileNo is xxxxxx"+mobno.substring(6)+ 
					"\nBalance is "+balance;
			dao.transactions(depositlog,cd);
			dao.update(cd);
			
		return depositlog;
		
	}


	@Override
	public String withdraw(String mobno, double amount,int pin)throws InvalidInputException {
		
		CustomerDetail cd = dao.withdraw(mobno);
		double balance = cd.getBalance();
		if(isValidamt(amount)&&cd.getPin()==pin){
			if(balance>amount){
				balance=balance-amount;
			}else{
				throw new InvalidInputException(" : Balance is Less than the entered Amount!");
			}
		}else{
			throw new InvalidInputException(" : Amount or PIN is Invalid!");
		}
		cd.setBalance(balance);
		String withdraw = new Date()+","+amount+" is debited from your Account Linked MobileNo is xxxxxx"+mobno.substring(6)+
				"\nBalance is "+balance;
		
		dao.transactions(withdraw,cd);
		dao.update(cd);
		
	return withdraw;
	}
	
	@Override
	public CustomerDetail showBalance(String mobno,int pin)throws InvalidInputException {
		CustomerDetail cd=dao.showBalance(mobno);
		if(isValidMobno(mobno)) {
			return cd; 
		}else{
			throw new InvalidInputException(" : PIN is Incorrect!!!");
		}
	}

	@Override
	public CustomerDetail displayDetails(String mobno,int pin)throws InvalidInputException {
		CustomerDetail cd=dao.displayDetails(mobno);
		if(isValidMobno(mobno)&&cd.getPin()==pin){
			return cd;
		}else{
			throw new InvalidInputException(" : PIN is Incorrect!!!");
		}
		
}
	
	@Override
	public String fundTransfer(String recievermobno, String sendermobno, double amount,int pin) {
		CustomerDetail cdsender=dao.fundTransfer(sendermobno);		
		if(isValidMobno(recievermobno)&&isValidMobno(sendermobno)&&isValidamt(amount)) {
			
				//withdraw(sendermobno,amount);
				double senderbalance = cdsender.getBalance();
				if(isValidamt(amount)&&cdsender.getPin()==pin){
					senderbalance=amount+senderbalance;
				}else{
					throw new InvalidInputException(" : Amount or PIN is Invalid!");
				}
				cdsender.setBalance(senderbalance);
				String withdrawTransfer = new Date()+","+amount+" is Transferred from your Account to MobileNo is "+recievermobno+ 
						"\nBalance is "+senderbalance;
				dao.transactions(withdrawTransfer,cdsender);
				dao.update(cdsender);
				
				//deposit(recievermobno,amt,pin);
				CustomerDetail cdreciever=dao.fundTransfer(recievermobno);
				if(cdreciever.getPin()==pin) {
					double recieverbalance = cdreciever.getBalance();
					if(isValidamt(amount)&&cdreciever.getPin()==pin){
						recieverbalance=amount+recieverbalance;
					}else{
						throw new InvalidInputException(" : Amount or PIN is Invalid!");
					}
					cdreciever.setBalance(recieverbalance);
					String depositTransfer = new Date()+","+amount+" is Transferred to your Account from MobileNo is "+sendermobno+ 
							"\nBalance is "+recieverbalance;
					dao.transactions(depositTransfer,cdreciever);
					dao.update(cdreciever);
			}
				String fundTransfer=amount+" amount is Transferred to the MobileNo: "+recievermobno;
				return fundTransfer;
		}
		else {
			throw new InvalidInputException(" :Invalid Details.");
		}
	}
	
	@Override
	public List<Passbook> printTransactions(String mobno,int pin){
		if(isValidMobno(mobno)) {
			return dao.printTransactions(mobno,pin);
		}
		else {
			throw new InvalidInputException(" : Invalid MobileNo.");
		}
	}
	
	public boolean isValidName(String name) {
		if (((name != null) && name.matches("[A-Z][a-z\\s]+"))) {
			return true;
		} else {
			throw new InvalidInputException(" : Name cannot be NULL (or) Enter Firstletter in Capital (or) INVALID Name.");
		}
	}
	
	public boolean isValidMobno(String mobno) {
		if((mobno != null)&& mobno.matches("[4-9][0-9]{9}")) {
			return true;
		}
		else {
			throw new InvalidInputException(" : Mobileno cannot be Null (or) INVALID Mobileno." );
		}
	}

	public boolean isValidAddress(String address) {
		//"\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"and[\\p{Punct}&&[#,.()-]]+\\d*+\\s?+[\\p{Alpha}+\\s?]*and(?=.*[0-9,/#-])+[A-Za-z0-9,]+([0-9]{5})$
		if((address!=null)&&address.matches("([0-9,/])+[A-Za-z0-9,\\s]+([0-9]{5})$")) {
			return true;
		}
		else {
			throw new InvalidInputException(" : Address cannot be Null (or) Enter DoorNo. First (or)INVALID Address.");
		}
	}
	
	public boolean isValidamt(double amt) {
				return amt>0;
	}
	
	public boolean isValidBalance(double bal){
		if(bal>=500||bal>=1000){
			return true;
		}else{
			throw new InvalidInputException(" : Initial Balance is Required!");
		}
	}

	
}
	

