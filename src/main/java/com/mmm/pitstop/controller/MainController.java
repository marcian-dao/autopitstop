package com.mmm.pitstop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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
	public String getCustomer(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		List<Customer>theCustomers = customerRepository.findAll();
		
		List<Customer> customers = new ArrayList<>();
		
		for(Customer tempCustomer : theCustomers) {
			
			String fName = tempCustomer.getFirstName();
			String lName = tempCustomer.getLastName();
			String email = tempCustomer.getEmail();
			
			if((fName.equalsIgnoreCase(theSearchName)) || (lName.equalsIgnoreCase(theSearchName)) || (email.equalsIgnoreCase(theSearchName))) {			
				
				customers.add(tempCustomer);
				
				model.addAttribute("customers", customers);		
				
				return "showCustomers";					
			}				
		}	
		
		return "redirect:/customers";
	}
	
	@RequestMapping("/formToPocess")
	public String formToProcess(Model model) {

		model.addAttribute("customer", new Customer());

		return "customerForm";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			
			return "customerForm";
			
		}else {
			
			theCustomer.setRegDate(new java.util.Date());
			
			customerRepository.save(theCustomer);
			
			return "redirect:/customers";
		}		
	}
	
	  @GetMapping("/showFormForUpdate/{theId}") 
	  public String updateCustomer(@PathVariable int theId, Model model) {
	  
		  Optional<Customer> result = customerRepository.findById(theId);
		  
		  if(result.isPresent()) {
			  
			  model.addAttribute("customer", result);
		  }
	    
	  	return "customerForm"; 
	  }
	  
	  @GetMapping("/deleteCustomer/{theId}")
	  public String removeCutomer(@PathVariable int theId) {
		  
		  
		  customerRepository.deleteById(theId);
		  
		  
		  return "redirect:/customers";
		  
	  }
}	  
 

