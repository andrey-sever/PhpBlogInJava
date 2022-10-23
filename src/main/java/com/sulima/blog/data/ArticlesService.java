package com.sulima.blog.data;

import com.sulima.blog.model.Articles;
import com.sulima.blog.model.Comments;
import com.sulima.blog.repository.ArticlesRepository;
import com.sulima.blog.repository.CommentsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final CommentsRepository commentsRepository;

    public void saveArticle(Articles article, HttpServletRequest request) {
        article.setDate(new Date().getTime());
        article.setAuthor(CookieFactory.getCookieByName(request, "login").getValue());
        articlesRepository.save(article);
    }

    public List<Articles> getListArticles() {
        return articlesRepository.findAll();
    }

    public Articles getArticleById(Integer id) {
        return articlesRepository.findById(id).get();
    }

    public void saveComment(Comments comment) {
        commentsRepository.save(comment);
    }

    public List<Comments> getListComments(Integer id) {
        return commentsRepository.findAllByArticleId(id);
    }

}
