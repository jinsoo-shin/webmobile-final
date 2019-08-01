package com.seongmo.springboot.controller;

import com.seongmo.springboot.entity.Member;
import com.seongmo.springboot.entity.Token;
import com.seongmo.springboot.service.MemberService;
import com.seongmo.springboot.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private TokenService tokenService;

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

    @PostMapping("tokens/get/{email}")
    @ResponseBody
    public Token getToken(@PathVariable("email") String email) throws Exception {
        return tokenService.getToken(email);
    }

    @PostMapping("tokens/getAll/{ranks}")
    @ResponseBody
    public List<Token> getAllToken(@PathVariable("ranks") int rank) throws Exception {
        return tokenService.getAllToken(rank);
    }

    @PostMapping("tokens/insert")
    @ResponseBody
    public void insertToken(@RequestBody Token token) throws Exception {
        tokenService.insertToken(token);
    }

    @PostMapping("tokens/update")
    @ResponseBody
    public void updateToken(@RequestBody Token token) throws Exception {
        tokenService.updateToken(token);
    }

    @PostMapping("tokens/delete/{email}")
    @ResponseBody
    public void deleteToken(@PathVariable("email") String email) throws Exception {
        tokenService.deleteToken(email);
    }

}
