package com.mmm.pitstop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mmm.pitstop.entity.Customer;

/*@RepositoryRestResource(path="testers")*/
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByOrderByLastNameAsc();

}
