package com.mmm.pitstop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String lastName;
	
	@Column(name="email")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String email;
	
	@Column(name="phone")
	
	@NotNull(message="is required")
	private String phone;
	
	@Column(name="make")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String make;
	
	@Column(name="model")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String model;
	
	@Column(name="color")
	@NotNull(message="is required")
	@Size( min=2, max=15, message="must be between 2 and 15 characters")
	private String color;
	
	@Column(name="year")
	@NotNull(message="is required")
	@Size( min=2, max=4, message="must be between a 2 and 4 characters")
	private String year;
	
	@Column(name="registration_date")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	public Customer() {
		
	}

	public Customer(String firstName, String lastName, String email, String phone, String make, String model,
			String color, String year, Date regDate) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	

	
}
