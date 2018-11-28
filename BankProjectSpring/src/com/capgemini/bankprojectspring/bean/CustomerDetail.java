package com.capgemini.bankprojectspring.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;



@Entity
@Table(name="customer")
public class CustomerDetail {
	@Id
	private String mobno;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long accountno;
	private String name;
	private String address;
	@Email
	private String emailId;
	private double balance;
	private int pin;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private List<Passbook> transactions=new ArrayList<Passbook>();
	
	public List<Passbook> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Passbook> transactionlist) {
		this.transactions = transactionlist;
	}

	public CustomerDetail() { 
		
	}

	public long getAccountno() {
		return accountno;
	}

	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setBalance(double balance){
		this.balance=balance;
	}
	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String toString(){
		return "AccountNo: "+accountno+"\nPin: "+pin+"\nName: "+name+"\nAddress: "+address+"\nMobileNo: "+mobno+"\nEmail ID: "+emailId+"Balance: "+balance+"\n";
	}
}
