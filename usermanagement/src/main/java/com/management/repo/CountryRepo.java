package com.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Country;
import com.management.entity.User;

public interface CountryRepo extends JpaRepository<Country ,Integer > {

}
