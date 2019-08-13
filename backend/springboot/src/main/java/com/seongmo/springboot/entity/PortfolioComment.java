package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "portfoliocomments")
@Getter
@Setter
public class PortfolioComment {
    @Id
    @GeneratedValue
    private @Column(name = "cno") Long cno;
    private @Column(name = "bno") Long bno;
    private @Column(name = "content") String content;
    private @Column(name = "created_at") LocalDate create_at;
    private @Column(name = "author") String author;
}
