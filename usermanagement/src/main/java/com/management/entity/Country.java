package com.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name="country_master")
public class Country {
	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer countryId;
   private String  countryname;
   
   
   
public Integer getCountryId() {
	return countryId;
}
public void setCountryId(Integer countryId) {
	this.countryId = countryId;

}

public String getCountryname() {
	return countryname;
}
public void setCountryname(String countryname) {
	this.countryname = countryname;
}
			
   
   
		   

}
