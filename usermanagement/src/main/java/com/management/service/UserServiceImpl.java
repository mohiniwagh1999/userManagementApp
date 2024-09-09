package com.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.LoginFormDTO;
import com.management.dto.RegisterFormDTO;
import com.management.dto.ResetPwdFormDTO;
import com.management.dto.UserDTO;
import com.management.entity.City;
import com.management.entity.Country;
import com.management.entity.State;
import com.management.entity.User;
import com.management.repo.CityRepo;
import com.management.repo.CountryRepo;
import com.management.repo.StateRepo;
import com.management.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private CountryRepo countryRepo;
	
    @Autowired
   	private StateRepo stateRepo;
    @Autowired
   	private CityRepo cityRepo;
    @Autowired
   	private UserRepo userRepo;
    @Autowired
   private EmailService emailService; 
	
	@Override
	public Map<Integer, String> getCountries() {
		// TODO Auto-generated method stub
		Map<Integer ,String> countriesMap=new HashMap<>();
		List<Country> countryList = countryRepo.findAll();
		
		countryList.forEach(c->{
			
			countriesMap.put(c.getCountryId(),c.getCountryname());
		});
		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		// TODO Auto-generated method stub
		Map<Integer ,String> stateMap=new HashMap<>();
		 List<State> statyeList = stateRepo.findByCountryId(countryId);
		 statyeList.stream().forEach(s->{
			 stateMap.put(s.getStateId(),s.getStatename()) ;
		 });
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		// TODO Auto-generated method stub
		Map<Integer,String> cityMap=new HashMap<>(); 
		List<City> cityList =  cityRepo.findByStateId(stateId);
		
		cityList.forEach(c->{
			
			cityMap.put(c.getCityId(), c.getCityname());
			
		});
		return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		// TODO Auto-generated method stub
		User byEmail = userRepo.findByEmail(email);
		
		if(byEmail!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean saveUser(RegisterFormDTO registerFormDTO) {
		// TODO Auto-generated method stub
		User user=new User();
		
		BeanUtils.copyProperties(registerFormDTO, user);
		Country country = countryRepo.findById(registerFormDTO.getCountryId()).orElse(null);
		user.setCountry(country);
		
		State state = stateRepo.findById(registerFormDTO. getStateId()).orElse(null);
		user.setState(state);
		
		City city = cityRepo.findById(registerFormDTO. getCityId()).orElse(null);
		user.setCity(city);
		String randomPwd = generateRandomPwd();
		user.setPwd(randomPwd);
		user.setPwdUpdate("NO");
		
		User saveUser= userRepo.save(user);
		if(null!=saveUser.getUserId())
		{
			String subject="Your Account Created";
			String body="Your Password To Login:"+randomPwd;
			String to=registerFormDTO.getEmail();
			emailService.sendEmail(to, subject, body);
			return true;
		}
		
		return false;
	}

	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
		if(user!=null)
		{
			UserDTO userDTO=new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		}
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO) {
		// TODO Auto-generated method stub
		String email = resetPwdFormDTO.getEmail();
		User entity = userRepo.findByEmail(email);
		entity.setPwd(resetPwdFormDTO.getNewPwd());
		entity.setPwdUpdate("YES");
		userRepo.save(entity);
		return true;
	}
	
	
	public String generateRandomPwd() {
		
		String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower="abcdefghijklmnopqrstuvwxyz";
		String alphabet=upper+lower;
		
		Random random=new Random();
		StringBuffer generatedPwd=new StringBuffer();
		for(int i=0;i<5;i++)
		{
			int randomIndex = random.nextInt(alphabet.length());
			generatedPwd.append(alphabet.charAt(randomIndex));
		}
		return generatedPwd.toString();
	}

}
