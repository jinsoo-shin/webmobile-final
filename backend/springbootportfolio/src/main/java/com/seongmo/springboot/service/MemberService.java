package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Member;

public interface MemberService {
    public void insertMember(Member member) throws Exception;
    public Member getMember(String email) throws Exception;
    public void updateMember(Member member) throws Exception;
    public void deleteMember(String email) throws Exception;
}
