package com.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.City;
import com.management.entity.User;

public interface CityRepo extends JpaRepository<City ,Integer > {
	
	public List<City> findByStateId(Integer stateId);

}
