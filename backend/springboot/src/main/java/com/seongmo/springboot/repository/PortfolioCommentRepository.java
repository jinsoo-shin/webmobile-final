package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.PortfolioComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioCommentRepository extends JpaRepository<PortfolioComment, Long> {
    List<PortfolioComment> findByBno(Long bno);
}
