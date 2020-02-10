package com.mmm.pitstop.service;

import java.util.List;

import com.mmm.pitstop.entity.Customer;

public interface CustomerService {
	
	
	List<Customer>findByOrderByFirstNameAsc();
	

}
