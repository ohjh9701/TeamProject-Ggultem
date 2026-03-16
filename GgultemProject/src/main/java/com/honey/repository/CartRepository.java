package com.honey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.honey.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("select c from Cart c") // 전체 조회를 원하신다면 이렇게!
	Page<Cart> findAllList(Pageable pageable);

	@EntityGraph(attributePaths = { "itemBoard", "itemBoard.itemList" })
	@Query("SELECT c FROM Cart c JOIN c.itemBoard i WHERE "
			+ "( (:searchType = 'title' AND i.title LIKE %:keyword%) OR "
			+ "  (:searchType = 'writer' AND i.writer LIKE %:keyword%) OR "
			+ "  (:searchType = 'content' AND i.content LIKE %:keyword%) OR "
			+ "  (:searchType = 'category' AND i.category LIKE %:keyword%) OR " + // 카테고리 추가
			"  (:searchType = 'location' AND i.location LIKE %:keyword%) OR " + // 지역 추가
			"  (:searchType = 'all' AND (i.title LIKE %:keyword% OR i.writer LIKE %:keyword% OR i.content LIKE %:keyword% OR i.category LIKE %:keyword% OR i.location LIKE %:keyword%)) ) "
			+ "OR "
			+ "( (:searchType IS NULL OR :searchType = '') AND (i.title LIKE %:keyword% OR i.writer LIKE %:keyword% OR i.content LIKE %:keyword% OR i.category LIKE %:keyword% OR i.location LIKE %:keyword%) )")
	Page<Cart> searchByCondition(@Param("searchType") String searchType, @Param("keyword") String keyword,
			Pageable pageable);
}
