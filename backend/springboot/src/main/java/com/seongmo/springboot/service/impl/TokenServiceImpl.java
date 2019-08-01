package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.Token;
import com.seongmo.springboot.repository.TokenRepository;
import com.seongmo.springboot.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void insertToken(Token token) throws Exception {
        tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllToken(int rank) throws Exception {
        if (rank == 0) {
            return tokenRepository.findAll();
        }
        return tokenRepository.getTokenByRank(rank);
    }

    @Override
    public Token getToken(String email) throws Exception {
        return tokenRepository.findById(email).get();
    }

    @Override
    public void updateToken(Token token) throws Exception {
        Optional<Token> ot =  tokenRepository.findById(token.getEmail());
        Token t = null;
        if(ot.isPresent()) {
            t = tokenRepository.findById(token.getEmail()).get();
        }

        if(!token.getToken().equals("") && !t.getToken().equals(token.getToken())) {
            t.setToken(token.getToken());
        }
        if(token.getRanks() != 0 && t.getRanks() != token.getRanks()) {
            t.setRanks(token.getRanks());
        }
        tokenRepository.save(t);
    }

    @Override
    public void deleteToken(String email) throws Exception {
        tokenRepository.deleteById(email);
    }
}
