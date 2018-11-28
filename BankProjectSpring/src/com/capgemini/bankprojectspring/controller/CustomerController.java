package com.capgemini.bankprojectspring.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.bankprojectspring.bean.CustomerDetail;
import com.capgemini.bankprojectspring.bean.Passbook;
import com.capgemini.bankprojectspring.exception.InvalidInputException;
import com.capgemini.bankprojectspring.service.ICustomerService;

@Controller
@RequestMapping(value="/control")
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	
	@RequestMapping(value="/menu")
	public String getMenu() {
		return "menu";		
	}
	
	@RequestMapping(value = "/createaccountc", method = RequestMethod.GET)
	public ModelAndView getmenu() {
		CustomerDetail customer = new CustomerDetail();
		return new ModelAndView("createaccount", "customer", customer);
	}

	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute(value = "customer") CustomerDetail customer) {

		try {
			String status = customerService.addCustomer(customer);
			return new ModelAndView("customerstatus", "message", status);
		} catch (InvalidInputException e) {
			return new ModelAndView("status", "message", e.getMessage());
		}
	}

	@RequestMapping(value = "/depositc", method = RequestMethod.GET)
	public ModelAndView depositController(@RequestParam(value = "mobileno") String mobileno,
			@RequestParam(value = "pin") Integer pin, @RequestParam(value = "amt") Integer amount) {
		try {

			String deposit = customerService.deposit(mobileno, amount, pin);
			return new ModelAndView("customerstatus", "message", deposit);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status", "message", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/withdrawc", method = RequestMethod.GET)
	public ModelAndView withdrawController(@RequestParam(value = "mobileno") String mobileno,
			@RequestParam(value = "pin") Integer pin, @RequestParam(value = "amt") Integer amount) {
		try {

			String withdraw = customerService.withdraw(mobileno, amount, pin);
			return new ModelAndView("customerstatus", "message", withdraw);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status", "message", e.getMessage());
		}
	}

	@RequestMapping(value = "/showbalancec", method = RequestMethod.GET)
	public ModelAndView showBalance(@RequestParam(value = "mobileno") String mobileno,
			@RequestParam(value = "pin") Integer pin) {
		try {

			CustomerDetail customer = customerService.showBalance(mobileno, pin);
			if (customer != null) {
				return new ModelAndView("showbalance", "customerbalance", customer);
			} else {
				return new ModelAndView("customerstatus", "message", "No customer in database");
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status", "message", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/fundtransferc", method = RequestMethod.GET)
	public ModelAndView fundTransferController(@RequestParam(value = "sendermobileno") String sendermobileno, @RequestParam(value = "recievermobileno") String recievermobileno,
			@RequestParam(value = "pin") Integer pin, @RequestParam(value = "amt") Integer amount) {
		try {

			String fundTransfer = customerService.fundTransfer(recievermobileno,sendermobileno, amount, pin);
			return new ModelAndView("customerstatus", "message", fundTransfer);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status", "message", e.getMessage());
		}
	}
	
	@RequestMapping(value="/printtransactionc", method=RequestMethod.GET)
	public ModelAndView printTransactionController(@RequestParam(value = "mobileno") String mobileno,
			@RequestParam(value = "pin") Integer pin) {
		try {
			List<Passbook> transactions=customerService.printTransactions(mobileno,pin);
			if(transactions.size()!=0) {				
				return new ModelAndView("printtransactions","transactions",transactions);
			}else {
				return new ModelAndView("customerstatus","message","No Transactions in database");
			}
		}catch(InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status","message",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/displaydetailsc", method = RequestMethod.GET)
	public ModelAndView displayDetailsController(@RequestParam(value = "mobileno") String mobileno,
			@RequestParam(value = "pin") Integer pin) {
		try {

			CustomerDetail customer = customerService.displayDetails(mobileno, pin);
			if (customer != null) {
				return new ModelAndView("displaydetails", "customerdetails", customer);
			} else {
				return new ModelAndView("customerstatus", "message", "No customer in database");
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return new ModelAndView("status", "message", e.getMessage());
		}
	}

}
