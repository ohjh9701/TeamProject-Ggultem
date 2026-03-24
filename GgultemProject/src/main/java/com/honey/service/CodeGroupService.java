package com.honey.service;

import com.honey.dto.CodeGroupDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;

public interface CodeGroupService {

	CodeGroupDTO get(String groupCode);

	String register(CodeGroupDTO codeGroupDTO);

	PageResponseDTO<CodeGroupDTO> list(SearchDTO searchDTO);

	void modify(CodeGroupDTO codeGroupDTO);

	void remove(String groupCode);

	




	
	
}
