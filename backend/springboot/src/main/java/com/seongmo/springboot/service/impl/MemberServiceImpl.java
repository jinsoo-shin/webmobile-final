package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.repository.MemberRepository;
import com.seongmo.springboot.service.MemberService;
import com.seongmo.springboot.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void insertMember(Member member) throws Exception {
        member.setCreate_at(LocalDate.now());
        memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMember() throws Exception {
        return (List<Member>)memberRepository.findAll();
    }

    @Override
    public Member getMember(String email) throws Exception {
        return memberRepository.findById(email).get();
    }

    @Override
    public void updateMember(Member member) throws Exception {
        Member m = memberRepository.findById(member.getEmail()).get();
        if (member.getAge() != m.getAge()) {
            m.setAge(member.getAge());
        }
        if (member.getRanks() != m.getRanks()) {
            m.setRanks(member.getRanks());
        }
        if (member.getAlbum() != null && member.getAlbum() != m.getAlbum()) {
            m.setAlbum(member.getAlbum());
        }
        if (member.getName() != null && member.getName() != m.getName()) {
            m.setName(member.getName());
        }
        if (member.getPassword() != null && member.getPassword() != m.getPassword()) {
            m.setPassword(member.getPassword());
        }
        memberRepository.save(m);
    }

    @Override
    public void deleteMember(String email) throws Exception {
        memberRepository.deleteById(email);
    }
}
