package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Portfolio;

public interface PortfolioService {
    public void insertPortfolio(Portfolio portfolio) throws Exception;
    public Portfolio getPortfolio(String author) throws Exception;
    public void updatePortfolio(Portfolio portfolio) throws Exception;
    public void deletePortfolio(String author) throws Exception;
}
