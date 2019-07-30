package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "portfolios")
@Setter
@Getter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Column(name = "author") String author;
    private @Column(name = "body") String body;
    @CreationTimestamp
    private @Column(name = "created_at") String createAt;
    private @Column(name = "img") String img;
    private @Column(name = "title") String title;
}