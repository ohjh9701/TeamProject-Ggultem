package com.honey.service;

import com.honey.dto.BoardDTO;
import com.honey.dto.NoticeDTO;
import com.honey.dto.PageRequestDTO;
import com.honey.dto.PageResponseDTO;
import com.honey.dto.SearchDTO;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    // 공지사항 상세 조회
    public NoticeDTO get(Long noticeId);

    // 공지사항 등록 (파일 업로드 포함)
    public Long register(NoticeDTO noticeDTO);

    // 공지사항 목록 (페이징/검색 처리)
    public PageResponseDTO<NoticeDTO> list(SearchDTO searchDTO);

    // 공지사항 수정
    public void modify(NoticeDTO noticeDTO);

    // 공지사항 삭제 (softDelete로 통일하는 거 잊지 말기)
    public void remove(Long noticeId);

    // 공지사항 이미지 관련 업데이트 (필요시)
    public void updateToImage(NoticeDTO noticeDTO);

}