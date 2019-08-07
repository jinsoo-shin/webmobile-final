package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Portfolio;

import java.util.List;

public interface PortfolioService {
    void insertPortfolio(Portfolio Portfolio) throws Exception;
    List<Portfolio> getAllPortfolio() throws Exception;
    Portfolio getPortfolio(Long bno) throws Exception;
    void updatePortfolio(Portfolio Portfolio) throws Exception;
    void deletePortfolio(Long bno) throws Exception;
}
