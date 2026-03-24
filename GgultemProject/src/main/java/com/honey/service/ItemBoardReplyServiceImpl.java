package com.honey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.honey.domain.ItemBoard;
import com.honey.domain.ItemBoardReply;
import com.honey.domain.Member;
import com.honey.dto.ItemBoardReplyDTO;
import com.honey.repository.ItemBoardReplyRepository;
import com.honey.repository.ItemBoardRepository;
import com.honey.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemBoardReplyServiceImpl implements ItemBoardReplyService {

	private final ItemBoardReplyRepository itemBoardReplyRepository;
	private final ItemBoardRepository itemBoardRepository;
	private final MemberRepository memberRepository;

	@Override
	public List<ItemBoardReplyDTO> list(Long itemId) {

		List<ItemBoardReply> replyList = itemBoardReplyRepository.getRepliesByItem(itemId);

		Map<Long, ItemBoardReplyDTO> dtoMap = replyList.stream()
				.map(reply -> ItemBoardReplyDTO.builder().replyNo(reply.getReplyNo())
						.itemId(reply.getItemBoard().getId()).email(reply.getMember().getEmail())
						.content(reply.getContent())
						.parentReplyNo(reply.getParent() != null ? reply.getParent().getReplyNo() : null).enabled(1)
						.build())
				.collect(Collectors.toMap(ItemBoardReplyDTO::getReplyNo, dto -> dto));

		List<ItemBoardReplyDTO> result = new ArrayList<>();

		for (ItemBoardReplyDTO dto : dtoMap.values()) {

			if (dto.getParentReplyNo() == null) {
				result.add(dto);
			} else {
				ItemBoardReplyDTO parent = dtoMap.get(dto.getParentReplyNo());
				if (parent != null) {
					parent.getChildList().add(dto);
				}
			}
		}

		return result;
	}

	@Override
	public Long register(ItemBoardReplyDTO dto) {
		ItemBoard itemBoard = itemBoardRepository.findById(dto.getItemId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다: " + dto.getItemId()));

		Member member = memberRepository.findById(dto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + dto.getEmail()));

		ItemBoardReply parentReply = null;
		if (dto.getParentReplyNo() != null && dto.getParentReplyNo() > 0) {
			parentReply = itemBoardReplyRepository.findById(dto.getParentReplyNo())
					.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부모 댓글입니다: " + dto.getParentReplyNo()));
		}

		ItemBoardReply reply = ItemBoardReply.builder().itemBoard(itemBoard).member(member).content(dto.getContent())
				.parent(parentReply).enabled(1).build();

		itemBoardReplyRepository.save(reply);

		return reply.getReplyNo();
	}

	@Override
	public void modify(ItemBoardReplyDTO dto) {

		ItemBoardReply reply = itemBoardReplyRepository.findById(dto.getReplyNo()).orElseThrow();

		reply.changeContent(dto.getContent());
	}

	@Override
	public void remove(Long replyNo) {

		ItemBoardReply reply = itemBoardReplyRepository.findById(replyNo).orElseThrow();

		reply.changeEnabled(0);
	}

}
