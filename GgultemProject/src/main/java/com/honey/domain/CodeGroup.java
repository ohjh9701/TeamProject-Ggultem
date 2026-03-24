package com.honey.domain;

import java.time.LocalDateTime;

import com.honey.common.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "codegroup")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeGroup extends BaseTimeEntity {
	@Id
	private String groupCode;
	private String groupName;
	private String useYn;
	private Integer enabled;
	
	private LocalDateTime dtdDate; // 삭제일
	
	

	public void changeStatus(int newStatus) {
		this.enabled = newStatus;
		LocalDateTime now = LocalDateTime.now(); // 변수로 고정

		switch (newStatus) {
		case 0: // 삭제
			this.dtdDate = now;
			break;
		case 1: // 활성화 (정지 해제)
			this.dtdDate = null;
			break;
		}
	}

	public void changeGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
