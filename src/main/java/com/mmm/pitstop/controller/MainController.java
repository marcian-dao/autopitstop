package com.mmm.pitstop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmm.pitstop.entity.Customer;
import com.mmm.pitstop.service.CustomerService;

@Controller
public class MainController {
	
	private CustomerService customerService;
	
	@Autowired
	public MainController(CustomerService theCustomerService ) {
		
		customerService = theCustomerService;
		
	}
	
	@RequestMapping("/")
	public String ShowHome() {
		
		System.out.println("It should go to the Home Page NOW");
		
		return "homePage";
	}
	
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		
		List<Customer> theCustomers = customerService.findByOrderByFirstNameAsc();
		
		model.addAttribute("customers", theCustomers);
		
		return "showCustomers";
	}
}

