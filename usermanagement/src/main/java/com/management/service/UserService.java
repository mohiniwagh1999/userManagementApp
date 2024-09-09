package com.management.service;

import java.util.Map;

import com.management.dto.LoginFormDTO;
import com.management.dto.RegisterFormDTO;
import com.management.dto.ResetPwdFormDTO;
import com.management.dto.UserDTO;

public interface UserService {
	
	
	public Map<Integer,String> getCountries();
	
	public Map<Integer,String> getStates(Integer countryId);
	public Map<Integer,String> getCities(Integer stateId);
	
	
	public boolean duplicateEmailCheck(String email);
	
	public boolean saveUser(RegisterFormDTO registerFormDTO);
	
	public UserDTO login(LoginFormDTO loginFormDTO);
	
	public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO);
	
	
	

}
