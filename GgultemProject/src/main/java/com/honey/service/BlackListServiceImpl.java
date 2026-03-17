package com.honey.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.honey.domain.BlackList;
import com.honey.dto.BlackListDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;
import com.honey.repository.BlackListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {
	private final ModelMapper modelMapper;
	private final BlackListRepository repository;

	@Override
	public BlackListDTO get(Long blId) {
		Optional<BlackList> result = repository.findById(blId);
		BlackList blackList = result.orElseThrow();
		
		BlackListDTO blackListDTO = modelMapper.map(blackList, BlackListDTO.class);
		
		return blackListDTO;
	}
	
	@Override
	public Long register(BlackListDTO blackListDTO) {
	    // ModelMapper 대신 Builder 패턴을 사용하여 명확하게 객체를 생성합니다.
	    // 이렇게 하면 매핑 충돌 에러가 발생하지 않습니다.
	    BlackList blackList = BlackList.builder()
	            .userId(blackListDTO.getUserId())
	            .reason(blackListDTO.getReason())
	            .adminId(blackListDTO.getAdminId())
	            .status(blackListDTO.getStatus())
	            .startDate(LocalDateTime.now()) // 시작일은 서버 현재 시간으로 강제 설정
	            .endDate(blackListDTO.getEndDate()) // 종료일은 DTO에서 받은 값 설정
	            .enabled(1) // 활성화 상태로 설정
	            .build();
	    
	    log.info("등록될 블랙리스트 엔티티: " + blackList);
	    
	    return repository.save(blackList).getBlId();
	}

	@Override
	public PageResponseDTO<BlackListDTO> list(SearchDTO searchDTO) {
		Pageable pageable = PageRequest.of(searchDTO.getPage() - 1, // 1 페이지가 0 이므로 주의
				searchDTO.getSize(), Sort.by("blId").descending());
		Page<BlackList> result = repository.findAllByEnabled(pageable);
		
		List<BlackListDTO> dtoList = result.getContent().stream().map(blackList -> {
			BlackListDTO dto = modelMapper.map(blackList, BlackListDTO.class);
	        return dto;
	    }).collect(Collectors.toList());

	long totalCount = result.getTotalElements();

	PageResponseDTO<BlackListDTO> responseDTO = PageResponseDTO.<BlackListDTO>withAll().dtoList(dtoList)
			.pageRequestDTO(searchDTO).totalCount(totalCount).build();

	return responseDTO;
	}
	
	@Override
	public void modify(BlackListDTO blackListDTO) {
		Optional<BlackList> result = repository.findById(blackListDTO.getBlId());
		BlackList blackList = result.orElseThrow();

		blackList.changeEndDate(blackListDTO.getEndDate());

	    repository.save(blackList);
	}
	
	@Override
	public void remove(Long BlId) {
		Optional<BlackList> result = repository.findById(BlId);
		BlackList blackList = result.orElseThrow();
		
		blackList.changeEnabled(0);

		repository.save(blackList);
	}
	
}
