package com.honey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.honey.domain.CodeGroup;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {

	@Query("select c from CodeGroup c where c.enabled = 1")
	Page<CodeGroup> findAllByEnabled(Pageable pageable);

	@Query("SELECT c FROM CodeGroup c WHERE c.enabled = 1 AND (" +
		       "  (:searchType = 'groupCode' AND c.groupCode LIKE %:keyword%) OR " +
		       "  (:searchType = 'groupName' AND c.groupName LIKE %:keyword%) OR " +
		       "  ((:searchType = 'all' OR :searchType IS NULL OR :searchType = '') AND " +
		       "   (c.groupCode LIKE %:keyword% OR c.groupName LIKE %:keyword%))" +
		       ")")
		Page<CodeGroup> searchByCondition(@Param("searchType") String searchType, 
		                                  @Param("keyword") String keyword, 
		                                  Pageable pageable);

}
