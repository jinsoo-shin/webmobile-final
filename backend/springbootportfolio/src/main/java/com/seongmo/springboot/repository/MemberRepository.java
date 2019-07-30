package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByEmail(String email);
}
