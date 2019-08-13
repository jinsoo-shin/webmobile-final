package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
public class Portfolio {
    @Id
    @GeneratedValue
    private @Column(name = "bno") Long bno;
    private @Column(name = "author") String author;
    private @Column(name = "content") String content;
    private @Column(name = "created_at") LocalDate create_at;
    private @Column(name = "img") String img;
    private @Column(name = "title") String title;
}
