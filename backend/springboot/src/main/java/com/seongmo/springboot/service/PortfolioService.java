package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Portfolio;

import java.util.List;

public interface PortfolioService {
    public void insertPortfolio(Portfolio Portfolio) throws Exception;
    public List<Portfolio> getAllPortfolio() throws Exception;
    public Portfolio getPortfolio(String author) throws Exception;
    public void updatePortfolio(Portfolio Portfolio) throws Exception;
    public void deletePortfolio(String author) throws Exception;
}
