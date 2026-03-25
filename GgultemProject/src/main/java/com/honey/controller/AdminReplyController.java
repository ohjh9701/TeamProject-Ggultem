package com.honey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honey.dto.BoardReplyDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;
import com.honey.service.BoardReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/reply")
@RequiredArgsConstructor
public class AdminReplyController {
	private final BoardReplyService boardReplyService;

	  // 댓글 목록
	@GetMapping("/list")
	 public PageResponseDTO<BoardReplyDTO> list(SearchDTO searchDTO) {

        return boardReplyService.adminReplyList(searchDTO);
    }
    // 댓글 삭제
    @PutMapping("/{replyNo}")
    public Map<String, String> remove(@PathVariable Long replyNo) {
        boardReplyService.adminRemove(replyNo);
        return Map.of("result", "success");
    }
	
	
	
}
