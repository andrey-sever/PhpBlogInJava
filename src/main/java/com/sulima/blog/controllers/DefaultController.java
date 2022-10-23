package com.sulima.blog.controllers;

import com.sulima.blog.data.CookieFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/reg.html")
    public String reg() {
        return "reg";
    }

    @RequestMapping("/auth.html")
    public String auth() {
        return "auth";
    }

    @RequestMapping("/article.html")
    public String article(HttpServletRequest request) {
        Cookie cookie = CookieFactory.getCookieByName(request, "login");
        if (cookie == null) {
            return "reg";
        }
        return "article";
    }

    @RequestMapping("/news.html")
    public String news() {
        return "news";
    }
}
