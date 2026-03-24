package com.honey.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeGroupDTO {
	private String groupCode;
	private String groupName;
	private String useYn;
	private Integer enabled;
	
	private LocalDateTime regDate;
	private LocalDateTime updDate;
	private LocalDateTime dtdDate;
}