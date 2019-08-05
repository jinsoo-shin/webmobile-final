package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.PortfolioComment;

import java.util.List;

public interface PortfolioCommentService {
    void insertPortfolioComment(PortfolioComment portfolioComment) throws Exception;
    List<PortfolioComment> getAllPortfolioComment(Long bno) throws Exception;
    PortfolioComment getPortfolioComment(Long cno) throws Exception;
    void updatePortfolioComment(PortfolioComment portfolioComment) throws Exception;
    void deletePortfolioComment(Long cno) throws Exception;
}
