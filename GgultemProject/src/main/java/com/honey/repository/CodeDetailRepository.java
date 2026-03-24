package com.honey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.honey.domain.CodeDetail;
import com.honey.domain.CodeDetailId;


public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {
	
	@Query("select c from CodeDetail c where c.enabled = 1 AND c.groupCode = :groupCode")
	Page<CodeDetail> findAllByEnabled(Pageable pageable, @Param("groupCode") String groupCode);
	
	@Query("SELECT c FROM CodeDetail c WHERE c.enabled = 1 AND c.groupCode = :groupCode AND (" +
		       "  (:searchType = 'codeValue' AND c.codeValue LIKE %:keyword%) OR " +
		       "  (:searchType = 'codeName' AND c.codeName LIKE %:keyword%) OR " +
		       "  ((:searchType = 'all' OR :searchType IS NULL OR :searchType = '') AND " +
		       "   (c.codeValue LIKE %:keyword% OR c.codeName LIKE %:keyword%))" +
		       ")")
		Page<CodeDetail> searchByCondition(@Param("searchType") String searchType, 
		                                  @Param("keyword") String keyword, 
		                                  Pageable pageable,
		                                  @Param("groupCode") String groupCode);
	@Query("SELECT NVL(MAX(sortSeq), 0) FROM CodeDetail c WHERE c.groupCode = :groupCode")
	int getMaxSortSeq(String groupCode);

}
