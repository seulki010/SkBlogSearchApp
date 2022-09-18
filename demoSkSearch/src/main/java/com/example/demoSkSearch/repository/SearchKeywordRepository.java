package com.example.demoSkSearch.repository;

import com.example.demoSkSearch.vo.SearchKeyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, String> {
    List<SearchKeyword> findAlLByOrderByHitcntDesc(Pageable pageable);
}
