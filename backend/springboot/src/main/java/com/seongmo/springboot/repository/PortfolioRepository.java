package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
