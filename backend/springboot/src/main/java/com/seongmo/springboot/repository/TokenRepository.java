package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    @Query("SELECT t from Token t WHERE ranks= :ranks")
    public List<Token> getTokenByRank(@Param("ranks") int ranks) throws Exception;
}
