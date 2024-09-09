package com.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.dto.LoginFormDTO;
import com.management.dto.QuoteApiResponseDTO;
import com.management.dto.RegisterFormDTO;
import com.management.dto.ResetPwdFormDTO;
import com.management.dto.UserDTO;
import com.management.service.DashboardService;
import com.management.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DashboardService dash;
	
	@GetMapping("/register")
	public String loadRegisterPage(Model model)
	{
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries",countriesMap);
		RegisterFormDTO registerFormDTO=new RegisterFormDTO();
		model.addAttribute("registerForm" ,registerFormDTO);
		return "register";
	}
	
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable Integer countryId )
	{
		Map<Integer, String> stateMap = userService.getStates(countryId);
	
		
		return stateMap;
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable Integer stateId )
	{
		Map<Integer, String> cityMap = userService.getCities(stateId);
	
		
		return cityMap;
	}
	
	
	@PostMapping("/register")
	public String handleRegisterPage(RegisterFormDTO registerFormDTO,Model model)
	{
		boolean status = userService.duplicateEmailCheck(registerFormDTO.getEmail());
		if(status)
		{
			model.addAttribute("emsg","Duplicate Email Found");
		}
		else
		{
			boolean saveUser = userService.saveUser(registerFormDTO);
			if(saveUser)
			{
				model.addAttribute("smsg","Register Successfully..Please Check Email !");
			}
			else
			{
				model.addAttribute("emsg","Registration Failed");
			}
		}
		model.addAttribute("registerForm" ,new RegisterFormDTO());
		model.addAttribute("countries",userService.getCountries());
		return "register";
	}
	
	
	@GetMapping("/")
	public String index(Model model)
	{
		
		LoginFormDTO loginDTO=new LoginFormDTO();
		model.addAttribute("loginForm" ,loginDTO);
		return "login";
	}
	
	
	@PostMapping("/login")
	public String handleLoginPage(LoginFormDTO loginDTO, Model model)
	{
		
		UserDTO userDTO = userService.login(loginDTO);
		if(userDTO==null)
		{
			model.addAttribute("emsg","Invalid Credential");
			model.addAttribute("loginForm" , new LoginFormDTO());
		} else {
			String pwdUpdate = userDTO.getPwdUpdate();
			
			if("YES".equals(pwdUpdate))
			{
				//display dashboard page
				return "redirect: dashboard";
			}
			
			else
			{
				//display resetpwd page
				return "redirect:resetpwd?email="+userDTO.getEmail();
			}
		
		}
		
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String getdashboard(Model model)
	{
		QuoteApiResponseDTO quote = dash.getQuote();
		model.addAttribute("quote",quote);
		return "dashboard";
	}
	
	@GetMapping("/resetpwd")
	public String loadResetPage(@RequestParam("email") String email,Model model)
	{
		
		ResetPwdFormDTO reset=new ResetPwdFormDTO();
		reset.setEmail(email);
		model.addAttribute("reset",reset);
		return "resetpwd";
	}
	
	
	@PostMapping("/resetpwd")
	public String handleResetPage(ResetPwdFormDTO reset ,Model model)
	{
		
		boolean resetPwd = userService.resetPwd(reset);
		if(resetPwd)
		{
			return "redirect:/dashboard";
		}
		
		return "resetpwd";
	}

}
