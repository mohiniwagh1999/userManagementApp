package com.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.State;
import com.management.entity.User;

public interface StateRepo  extends JpaRepository<State ,Integer >{
	
	public List<State> findByCountryId(Integer countryId);

}
