package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Token;

import java.util.List;

public interface TokenService {
    public void insertToken(Token token) throws Exception;
    public List<Token> getAllToken(int rank) throws Exception;
    public Token getToken(String email) throws Exception;
    public void updateToken(Token token) throws Exception;
    public void deleteToken(String email) throws Exception;
}
