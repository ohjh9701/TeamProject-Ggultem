package com.honey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honey.domain.ItemBoard;

public interface ItemBoardRepository extends JpaRepository<ItemBoard, Long> {

}
