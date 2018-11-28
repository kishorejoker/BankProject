package com.capgemini.bankprojectspring.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.bankprojectspring.bean.CustomerDetail;
import com.capgemini.bankprojectspring.bean.Passbook;
import com.capgemini.bankprojectspring.exception.InvalidInputException;

@Repository
public class ImpDao implements IDao {
	CustomerDetail cd = new CustomerDetail();
	
	@PersistenceContext
	private EntityManager entityManager;

	public boolean customerDetails(CustomerDetail cd) {
		
		entityManager.persist(cd);
		String createTransaction=new Date()+" - Account created!";
		Passbook trans = new Passbook(createTransaction,cd);
		entityManager.persist(trans);
		
		return true;
		
	}

	@Override
	public CustomerDetail deposit(String mobno) {
		
		if(entityManager.find(CustomerDetail.class,mobno)!=null){
			CustomerDetail customer = entityManager.find(CustomerDetail.class, mobno);
			return customer;
		}else{
			throw new InvalidInputException(" : Deposit Not Done Mobile Number Doesn't Exist!");
		}
	
	}

	@Override
	public CustomerDetail withdraw(String mobno) {

		if(entityManager.find(CustomerDetail.class,mobno)!=null){
			CustomerDetail customer = entityManager.find(CustomerDetail.class, mobno);
			return customer;
		}else{
			throw new InvalidInputException(" : WithDraw Not Done Mobile Number Doesn't Exist!");
		}
		
	}
	
	@Override
	public CustomerDetail showBalance(String mobno) {

		if(entityManager.find(CustomerDetail.class,mobno)!=null){
			CustomerDetail customer = entityManager.find(CustomerDetail.class, mobno);
			return customer;
		}else{
			throw new InvalidInputException(" : Mobile Number Doesn't Exist!");
		}
	}

	@Override
	public CustomerDetail displayDetails(String mobno) {

		if(entityManager.find(CustomerDetail.class,mobno)!=null){
			CustomerDetail customer = entityManager.find(CustomerDetail.class, mobno);
			return customer;
		}else{
			throw new InvalidInputException(" : Mobile Number Doesn't Exist!");
		}
	}
	
	@Override
	public CustomerDetail fundTransfer(String mobno) {

		CustomerDetail  customer=null;
		if(entityManager.find(CustomerDetail.class,mobno)!=null){
			 customer=entityManager.find(CustomerDetail.class,mobno);
		}
		 return customer;
	}
	
	@Override
	public List<Passbook> printTransactions(String mobno,int pin){

		List<Passbook> trans;
		if(entityManager.find(CustomerDetail.class,mobno) != null) {
			CustomerDetail customer=entityManager.find(CustomerDetail.class,mobno);
			if(customer.getPin()==pin){
			String jql="SELECT * FROM transactions where mobno=?";
			 Query query =
				      entityManager.createNativeQuery(jql,Passbook.class);
			 query.setParameter(1, mobno);
			 trans = query.getResultList();
			 }else{
				 throw new InvalidInputException(" : PIN is Incorrect!!!");
			 }
			 return trans;
		}
		else {
			throw new InvalidInputException(" : Invalid MobileNo.");
		}
	}

	@Override
	public void update(CustomerDetail customer){
		
		entityManager.merge(customer);
	}

	@Override
	public void transactions(String log,CustomerDetail cd) {
		
		Passbook transaction = new Passbook(log,cd);
		transaction.setCustomer(cd);
		entityManager.persist(transaction);
	}
}



