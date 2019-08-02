package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
}
