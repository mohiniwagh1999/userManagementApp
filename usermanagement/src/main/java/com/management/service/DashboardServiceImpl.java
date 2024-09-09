package com.management.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.management.dto.QuoteApiResponseDTO;

@Service
public class DashboardServiceImpl  implements DashboardService{

	private String api="https://dummyjson.com/quotes/random";
	@Override
	public 	QuoteApiResponseDTO getQuote() {
		// TODO Auto-generated method stub
		RestTemplate rs=new RestTemplate();
		ResponseEntity<QuoteApiResponseDTO> forEntity = rs.getForEntity(api, QuoteApiResponseDTO.class);
		QuoteApiResponseDTO body = forEntity.getBody();
		return body;
	}

}
