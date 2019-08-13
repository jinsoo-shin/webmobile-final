package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.Portfolio;
import com.seongmo.springboot.repository.PortfolioRepository;
import com.seongmo.springboot.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Override
    public void insertPortfolio(Portfolio portfolio) throws Exception {
        portfolio.setCreate_at(LocalDate.now().plusDays(1));
        portfolioRepository.save(portfolio);
    }

    @Override
    public List<Portfolio> getAllPortfolio() throws Exception {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio getPortfolio(Long bno) throws Exception {
        return portfolioRepository.findById(bno).get();
    }

    @Override
    public void updatePortfolio(Portfolio portfolio) throws Exception {
        Portfolio p = portfolioRepository.findById(portfolio.getBno()).get();
        if(p != null) {
            p.setContent(portfolio.getContent());
            p.setImg(portfolio.getImg());
            p.setTitle(portfolio.getTitle());
            portfolioRepository.save(p);
        }
    }

    @Override
    public void deletePortfolio(Long bno) throws Exception {
        portfolioRepository.deleteById(bno);
    }
}
