package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.Member;
import com.seongmo.springboot.repository.MemberRepository;
import com.seongmo.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void insertMember(Member member) throws Exception {

    }

    @Override
    public Member getMember(String email) throws Exception {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void updateMember(Member member) throws Exception {

    }

    @Override
    public void deleteMember(String email) throws Exception {

    }
}
