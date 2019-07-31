package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Setter
@Getter
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Column(name = "email") String email;
    private @Column(name = "age") Long age;
    private @Column(name = "album") String album;
    @CreationTimestamp
    private @Column(name = "created_at") String createAt;
    private @Column(name = "name") String name;
    private @Column(name = "password") String password;
    private @Column(name = "ranks") String ranks;
}