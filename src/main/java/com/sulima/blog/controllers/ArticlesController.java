package com.sulima.blog.controllers;

import com.sulima.blog.data.ArticlesService;
import com.sulima.blog.data.MainService;
import com.sulima.blog.model.Articles;
import com.sulima.blog.model.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticlesController {

    private final MainService mainService;
    private final ArticlesService articlesService;

    @PostMapping("/article_add")
    public String articleAdd(@Validated Articles articles, BindingResult bindingResult, HttpServletRequest request) {
        String error = mainService.getError(bindingResult);
        if (error == "1") {
            articlesService.saveArticle(articles, request);
        }
        return error;
    }

    @PostMapping("/list_articles")
    public List<Articles> listArticles() {
        return articlesService.getListArticles();
    }

    @GetMapping(path = "/article_one/{id}")
    public Articles articleOne(@PathVariable Integer id) {
        return articlesService.getArticleById(id);
    }

    @PostMapping("/comments_list")
    public List<Comments> commentsList(@RequestParam Integer id) {
        return articlesService.getListComments(id);
    }

    @PostMapping("/comment_add")
    public String commentAdd(@Validated Comments comment, BindingResult bindingResult) {
        String error = mainService.getError(bindingResult);
        if (error == "1") {
            articlesService.saveComment(comment);
        }
        return error;
    }
}

