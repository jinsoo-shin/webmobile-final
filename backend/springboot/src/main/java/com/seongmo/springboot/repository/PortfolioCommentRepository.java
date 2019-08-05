package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Portfolio;
import com.seongmo.springboot.entity.PortfolioComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PortfolioCommentRepository extends JpaRepository<PortfolioComment, Long> {
    List<PortfolioComment> findByBno(Long bno);
}
