package com.honey.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.honey.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "chatroom")
@SequenceGenerator(name = "CHATROOM_SEQ_GEN",
		sequenceName = "CHATROOM_SEQ",
		initialValue = 1,
		allocationSize = 1)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATROOM_SEQ_GEN")
	private Long roomId;
	
	private Long itemId;   // 관련 상품 ID
    private String sellerId; // 판매자 닉네임/ID
    private String buyerId;  // 구매자 닉네임/ID
	private String roomName;
	private Integer enabled; //논리 삭제 -> 판매자/구매자별 삭제 표시
	private Boolean buyerLeft; //판매자 채팅방 나가기
	private Boolean sellerLeft; //구매자 채팅방 나가기
	private LocalDateTime dtdDate;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name = "chat_messages")
	@Builder.Default
	private List<ChatMessages> chatMessages = new ArrayList();
	
	public void changeEnabled(int enabled) {
		this.enabled = enabled;
		if(enabled == 0) {
			this.dtdDate = LocalDateTime.now();
		}
	}
	
	public void changeRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public void changeRoomBuyerLeft(boolean buyerLeft) {
		this.buyerLeft = buyerLeft;
	}
	
	public void changeRoomSellerLeft(boolean sellerLeft) {
		this.sellerLeft = sellerLeft;
	}
	
	
}
