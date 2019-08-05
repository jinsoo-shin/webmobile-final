package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByAuthor(String author);
    void deleteByAuthor(String author);
}
