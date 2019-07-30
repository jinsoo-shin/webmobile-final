package com.seongmo.springboot.controller;

import com.seongmo.springboot.entity.Member;
import com.seongmo.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = {"/getMember" }, method = RequestMethod.GET)
    public Member getMember(String email) throws Exception {
        return memberService.getMember(email);
    }

}
