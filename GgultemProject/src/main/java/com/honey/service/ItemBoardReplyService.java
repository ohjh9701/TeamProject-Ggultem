package com.honey.service;

import java.util.List;

import com.honey.dto.ItemBoardReplyDTO;

public interface ItemBoardReplyService {

	public List<ItemBoardReplyDTO> list(Long itemId);

	public Long register(ItemBoardReplyDTO dto);

	public void modify(ItemBoardReplyDTO dto);

	public void remove(Long replyNo);

}
