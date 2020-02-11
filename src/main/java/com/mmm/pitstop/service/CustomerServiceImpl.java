package com.mmm.pitstop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.pitstop.dao.CustomerRepository;
import com.mmm.pitstop.entity.Customer;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		
		customerRepository = theCustomerRepository;
		
	}
	
	@Override
	public List<Customer> findByOrderByLastNameAsc() {		
		
		return customerRepository.findByOrderByLastNameAsc();
	}

}
