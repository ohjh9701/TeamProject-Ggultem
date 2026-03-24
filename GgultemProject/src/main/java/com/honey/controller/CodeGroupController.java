package com.honey.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honey.dto.CodeGroupDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;
import com.honey.service.CodeGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/codegroup")
public class CodeGroupController {

	private final CodeGroupService service;
	
	@GetMapping("/{groupCode}")
	public CodeGroupDTO getCodeGroup(@PathVariable(name = "groupCode") String groupCode) {
		return service.get(groupCode);
	}
	
	@PostMapping("/")
	public Map<String, String> register(@RequestBody CodeGroupDTO codeGroupDTO) {
		String groupCode = service.register(codeGroupDTO);
		return Map.of("groupCode", groupCode);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<CodeGroupDTO> list(SearchDTO searchDTO) {
		return service.list(searchDTO);
	}
	
	@PutMapping("/{groupCode}")
	public Map<String, String> modify(@PathVariable(name = "groupCode") String groupCode, @RequestBody CodeGroupDTO codeGroupDTO) {
		codeGroupDTO.setGroupCode(groupCode);
		service.modify(codeGroupDTO);
		return Map.of("RESULT", "SUCCESS");
	}
	
	@DeleteMapping("/{groupCode}")
	public Map<String, String> remove(@PathVariable(name = "groupCode") String groupCode) {
		service.remove(groupCode);
		return Map.of("RESULT", "SUCCESS");
	}
	
}
	   


