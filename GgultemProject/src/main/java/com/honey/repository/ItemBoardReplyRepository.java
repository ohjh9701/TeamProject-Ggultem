package com.honey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.honey.domain.ItemBoardReply;

public interface ItemBoardReplyRepository extends JpaRepository<ItemBoardReply, Long>{

	@Query("SELECT r FROM ItemBoardReply r " +
	           "WHERE r.itemBoard.id = :id AND r.enabled = 1 " +
	           "ORDER BY r.replyNo ASC")
	    List<ItemBoardReply> getRepliesByItem(@Param("id") Long id);
}
