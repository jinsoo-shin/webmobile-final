package com.seongmo.springboot.controller;

import com.seongmo.springboot.entity.Member;
import com.seongmo.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private MemberService memberService;

    @PostMapping("members/get/{email}")
    @ResponseBody
    public Member getMember(@PathVariable("email") String email) throws Exception {
        return memberService.getMember(email);
    }

    @PostMapping("members/getAll")
    @ResponseBody
    public List<Member> getAllMember() throws Exception {
        return memberService.getAllMember();
    }

    @PostMapping("members/insert")
    @ResponseBody
    public void insertMember(@RequestBody Member member) throws Exception {
        memberService.insertMember(member);
    }

    @PostMapping("members/update")
    @ResponseBody
    public void updateMember(@RequestBody Member member) throws Exception {
        memberService.updateMember(member);
    }

    @PostMapping("members/delete/{email}")
    @ResponseBody
    public void deleteMember(@PathVariable("email") String email) throws Exception {
        memberService.deleteMember(email);
    }
}
