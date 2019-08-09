package com.seongmo.springboot.controller;

import com.seongmo.springboot.entity.*;
import com.seongmo.springboot.service.*;
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
    @Autowired
    private PortfolioCommentService portfolioCommentService;
    @Autowired
    private  PostCommentService postCommentService;

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
    public Portfolio getPortfolio(@PathVariable("bno") Long bno) throws Exception {
        try {
            return portfolioService.getPortfolio(bno);
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

    @PostMapping("portfolios/delete/{bno}")
    @ResponseBody
    public void deletePortfolio(@PathVariable("bno") Long bno) throws Exception {
        try {
            portfolioService.deletePortfolio(bno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("posts/get/{bno}")
    @ResponseBody
    public Post getPost(@PathVariable("bno") Long bno) throws Exception {
        try {
            return postService.getPost(bno);
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

    @PostMapping("posts/delete/{bno}")
    @ResponseBody
    public void deletePost(@PathVariable("bno") Long bno) throws Exception {
        try {
            postService.deletePost(bno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portcomment/get/{cno}")
    @ResponseBody
    public PortfolioComment getPortfolioComment(@PathVariable("cno") Long cno) throws Exception {
        try {
            return portfolioCommentService.getPortfolioComment(cno);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 댓글 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("portcomment/getAll/{bno}")
    @ResponseBody
    public List<PortfolioComment> getAllPortfolioComment(@PathVariable("bno") Long bno) throws Exception {
        try {
            return portfolioCommentService.getAllPortfolioComment(bno);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 댓글 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("portcomment/insert")
    @ResponseBody
    public void insertPortfolioComment(@RequestBody PortfolioComment p) throws Exception {
        try {
            portfolioCommentService.insertPortfolioComment(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portcomment/update")
    @ResponseBody
    public void updatePortfolioComment(@RequestBody PortfolioComment p) throws Exception {
        try {
            portfolioCommentService.updatePortfolioComment(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("portcomment/delete/{cno}")
    @ResponseBody
    public void deletePortfolioComment(@PathVariable("cno") Long cno) throws Exception {
        try {
            portfolioCommentService.deletePortfolioComment(cno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("postcomment/get/{cno}")
    @ResponseBody
    public PostComment getPostComment(@PathVariable("cno") Long cno) throws Exception {
        try {
            return postCommentService.getPostComment(cno);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 댓글 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("postcomment/getAll/{bno}")
    @ResponseBody
    public List<PostComment> getAllPostComment(@PathVariable("bno") Long bno) throws Exception {
        try {
            return postCommentService.getAllPostComment(bno);
        }catch (Exception e) {
            System.out.println("존재하지 않는 포트폴리오 댓글 조회 message: " + e);
            return null;
        }
    }

    @PostMapping("postcomment/insert")
    @ResponseBody
    public void insertPostComment(@RequestBody PostComment p) throws Exception {
        try {
            postCommentService.insertPostComment(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("postcomment/update")
    @ResponseBody
    public void updatePostComment(@RequestBody PostComment p) throws Exception {
        try {
            postCommentService.updatePostComment(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("postcomment/delete/{cno}")
    @ResponseBody
    public void deletePostComment(@PathVariable("cno") Long cno) throws Exception {
        try {
            postCommentService.deletePostComment(cno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("analytics/getToken")
    @ResponseBody
    public String getAnalyticsToken() throws Exception {
        MyAuth.sendGet();
        return "MyAuth.getToken()";
    }
}
