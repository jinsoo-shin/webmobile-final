package com.seongmo.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tokens")
@Setter
@Getter
public class Token {

    @Id
    private @Column(name = "email") String email;
    private @Column(name = "token") String token;
    private @Column(name = "ranks") int ranks;
}