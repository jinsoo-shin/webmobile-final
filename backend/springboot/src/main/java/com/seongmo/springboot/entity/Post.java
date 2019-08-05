package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private @Column(name = "bno") Long bno;
    private @Column(name = "author") String author;
    private @Column(name = "content") String content;
    @CreationTimestamp
    private @Column(name = "created_at") Timestamp create_at;
    private @Column(name = "title") String title;
}
