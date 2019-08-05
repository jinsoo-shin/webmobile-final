package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
public class Portfolio {
    @Id
    @GeneratedValue
    private @Column(name = "bno") Long bno;
    private @Column(name = "author") String author;
    private @Column(name = "body") String body;
    @CreationTimestamp
    private @Column(name = "created_at") Timestamp create_at;
    private @Column(name = "img") String img;
    private @Column(name = "title") String title;
}
