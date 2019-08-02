package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.Portfolio;
import com.seongmo.springboot.repository.PortfolioRepository;
import com.seongmo.springboot.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Override
    public void insertPortfolio(Portfolio Portfolio) throws Exception {
        portfolioRepository.save(Portfolio);
    }

    @Override
    public List<Portfolio> getAllPortfolio() throws Exception {
        return (List<Portfolio>)portfolioRepository.findAll();
    }

    @Override
    public Portfolio getPortfolio(String email) throws Exception {
        return portfolioRepository.findById(email).get();
    }

    @Override
    public void updatePortfolio(Portfolio portfolio) throws Exception {
        Portfolio p = portfolioRepository.findById(portfolio.getAuthor()).get();
        if(p != null) {
            p.setBody(portfolio.getBody());
            p.setImg(portfolio.getImg());
            p.setTitle(portfolio.getTitle());
            portfolioRepository.save(p);
        }
    }

    @Override
    public void deletePortfolio(String email) throws Exception {
        portfolioRepository.deleteById(email);
    }
}
