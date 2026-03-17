package com.honey.service;

import com.honey.dto.BlackListDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;

public interface BlackListService {

	public BlackListDTO get(Long blId);

	public Long register(BlackListDTO blackListDTO);

	public void modify(BlackListDTO blackListDTO);

	public PageResponseDTO<BlackListDTO> list(SearchDTO searchDTO);

	public void remove(Long blId);




	
	
}
