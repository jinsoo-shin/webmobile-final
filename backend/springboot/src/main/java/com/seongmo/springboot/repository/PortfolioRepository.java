package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, String> {
}
