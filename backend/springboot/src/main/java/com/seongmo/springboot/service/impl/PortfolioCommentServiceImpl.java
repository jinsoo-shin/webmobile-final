package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.PortfolioComment;
import com.seongmo.springboot.repository.PortfolioCommentRepository;
import com.seongmo.springboot.service.PortfolioCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PortfolioCommentServiceImpl implements PortfolioCommentService {
    @Autowired
    PortfolioCommentRepository portfolioCommentRepository;

    @Override
    public void insertPortfolioComment(PortfolioComment portfolioComment) throws Exception {
        portfolioComment.setCreate_at(LocalDate.now().plusDays(1));
        portfolioCommentRepository.save(portfolioComment);
    }

    @Override
    public List<PortfolioComment> getAllPortfolioComment(Long bno) throws Exception {
        return portfolioCommentRepository.findByBno(bno);
    }

    @Override
    public PortfolioComment getPortfolioComment(Long cno) throws Exception {
        return portfolioCommentRepository.findById(cno).get();
    }

    @Override
    public void updatePortfolioComment(PortfolioComment comment) throws Exception {
        PortfolioComment p = portfolioCommentRepository.findById(comment.getCno()).get();
        p.setContent(comment.getContent());
        p.setAuthor(comment.getAuthor());
        portfolioCommentRepository.save(p);
    }

    @Override
    public void deletePortfolioComment(Long cno) throws Exception {
        portfolioCommentRepository.deleteById(cno);
    }
}
