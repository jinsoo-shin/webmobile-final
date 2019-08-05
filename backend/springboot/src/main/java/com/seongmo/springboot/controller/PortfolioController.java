package com.seongmo.springboot.controller;

import com.seongmo.springboot.entity.Member;
import com.seongmo.springboot.entity.Portfolio;
import com.seongmo.springboot.entity.Post;
import com.seongmo.springboot.entity.Token;
import com.seongmo.springboot.service.MemberService;
import com.seongmo.springboot.service.PortfolioService;
import com.seongmo.springboot.service.PostService;
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
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private PostService postService;

    @PostMapping("members/get/{email}")
    @ResponseBody
    public Member getMember(@PathVariable("email") String email) throws Exception {
        try {
            return memberService.getMember(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("members/getAll")
    @ResponseBody
    public List<Member> getAllMember() throws Exception {
        try {
            return memberService.getAllMember();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("members/insert")
    @ResponseBody
    public void insertMember(@RequestBody Member member) throws Exception {
        try {
            memberService.insertMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("members/update")
    @ResponseBody
    public void updateMember(@RequestBody Member member) throws Exception {
        try {
            memberService.updateMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("members/delete/{email}")
    @ResponseBody
    public void deleteMember(@PathVariable("email") String email) throws Exception {
        try {
            memberService.deleteMember(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("tokens/get/{email}")
    @ResponseBody
    public Token getToken(@PathVariable("email") String email) throws Exception {
        try {
            return tokenService.getToken(email);
        }catch (Exception e) {
            System.out.println("존재하지 않는 토큰 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("tokens/getAll/{ranks}")
    @ResponseBody
    public List<Token> getAllToken(@PathVariable("ranks") int rank) throws Exception {
        try {
            return tokenService.getAllToken(rank);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("tokens/insert")
    @ResponseBody
    public void insertToken(@RequestBody Token token) throws Exception {
        try {
            tokenService.insertToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("tokens/update")
    @ResponseBody
    public void updateToken(@RequestBody Token token) throws Exception {
        try {
            tokenService.updateToken(token);
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    @PostMapping("tokens/delete/{email}")
    @ResponseBody
    public void deleteToken(@PathVariable("email") String email) throws Exception {
        try {
            tokenService.deleteToken(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portfolios/get/{author}")
    @ResponseBody
    public Portfolio getPortfolio(@PathVariable("author") String author) throws Exception {
        try {
            return portfolioService.getPortfolio(author);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("portfolios/getAll")
    @ResponseBody
    public List<Portfolio> getAllPortfolio() throws Exception {
        try {
            return portfolioService.getAllPortfolio();
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("portfolios/insert")
    @ResponseBody
    public void insertPortfolio(@RequestBody Portfolio portfolio) throws Exception {
        try {
            portfolioService.insertPortfolio(portfolio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portfolios/update")
    @ResponseBody
    public void updatePortfolio(@RequestBody Portfolio portfolio) throws Exception {
        try {
            portfolioService.updatePortfolio(portfolio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portfolios/delete/{author}")
    @ResponseBody
    public void deletePortfolio(@PathVariable("author") String author) throws Exception {
        try {
            portfolioService.deletePortfolio(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("posts/get/{author}")
    @ResponseBody
    public Post getPost(@PathVariable("author") String author) throws Exception {
        try {
            return postService.getPost(author);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포스트 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("posts/getAll")
    @ResponseBody
    public List<Post> getAllPost() throws Exception {
        try {
            return postService.getAllPost();
        }catch (Exception e) {
            System.out.println("존재하지 않는 포스트 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("posts/insert")
    @ResponseBody
    public void insertPost(@RequestBody Post post) throws Exception {
        try {
            postService.insertPost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("posts/update")
    @ResponseBody
    public void updatePost(@RequestBody Post post) throws Exception {
        try {
            postService.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("posts/delete/{author}")
    @ResponseBody
    public void deletePost(@PathVariable("author") String author) throws Exception {
        try {
            postService.deletePost(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
