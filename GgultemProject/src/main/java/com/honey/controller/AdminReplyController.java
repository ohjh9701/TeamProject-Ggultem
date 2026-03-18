package com.honey.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honey.dto.BoardReplyDTO;
import com.honey.service.BoardReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/reply")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminReplyController {
	private final BoardReplyService service;

	// 댓글 전체 조회 (enabled 상관없이)
	@GetMapping("/list/{boardNo}")
	public List<BoardReplyDTO> list(@PathVariable Integer boardNo) {
		return service.adminList(boardNo);
	}

	@PutMapping("/{replyNo}")
	public void remove(@PathVariable Long replyNo) {
		service.adminRemove(replyNo);
	}
}
