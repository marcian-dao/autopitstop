package com.mmm.pitstop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mmm.pitstop.dao.CustomerRepository;
import com.mmm.pitstop.entity.Customer;
import com.mmm.pitstop.service.CustomerService;

@Controller
public class MainController {
	
	private CustomerService customerService;
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public MainController(CustomerService theCustomerService, CustomerRepository  theCustomerRepository) {
		
		customerService = theCustomerService;
		customerRepository = theCustomerRepository;
		
	}
	
	@RequestMapping("/")
	public String ShowHome() {
		
		System.out.println("It should go to the Home Page NOW");
		
		return "homePage";
	}
	
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		
		List<Customer> theCustomers = customerService.findByOrderByLastNameAsc();
		
		model.addAttribute("customers", theCustomers);
		
		return "showCustomers";
	}
	
	@GetMapping("/customer")
	public String getCustomer(@RequestParam int theId, Model model) {
		
		Optional<Customer> result = customerRepository.findById(theId);
		
		Customer theCustomer = null;
		
		if(result.isPresent()) {
			
			theCustomer = result.get();
			
		}else {
			throw new RuntimeException("Not Found!" + theId);
		}
		
		model.addAttribute("theCustomer", theCustomer);
		
		
		return "showTheCustomer";
		
	}
	
	@RequestMapping("/formToPocess")
	public String formToProcess(Model model) {

		model.addAttribute("customer", new Customer());

		return "customerForm";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		theCustomer.setRegDate(new java.util.Date());
		
		customerRepository.save(theCustomer);
		
		return "redirect:/customers";
	}
	

	  @GetMapping("/showFormForUpdate") 
	  public String updateCustomer(@RequestParam("customerId") int theId, Model model) {
	  
		  Optional<Customer> result = customerRepository.findById(theId);
		  
		  if(result.isPresent()) {
			  
			  model.addAttribute("customer", result);
		  }
	    
	  	return "customerForm"; 
	  }
	  
	  @GetMapping("/deleteCustomer")
	  public String removeCutomer(@RequestParam("customerId") int theId) {
		  
		  
		  customerRepository.deleteById(theId);
		  
		  
		  return "redirect:/customers";
		  
	  }
}	  
 

