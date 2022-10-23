package com.sulima.blog.controllers;

import com.sulima.blog.data.CookieFactory;
import com.sulima.blog.data.MainService;
import com.sulima.blog.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/registration")
    public String registration(@Validated Users user, BindingResult bindingResult) {
        String error = mainService.getError(bindingResult);
        if (error == "1") {
            mainService.saveUser(user);
        }
        return error;
    }

    @PostMapping("/authorization")
    public String authorization(@RequestParam("login") String login, @RequestParam("pass") String pass,
                                HttpServletResponse response) {
        String code = mainService.getErrorPass(login, pass);

        //установим куки
        if (code == "1") {
            CookieFactory.addCookie(response, login);
        }
        return code;
    }

    @PostMapping("/login_by_cookies")
    public Users loginByCookies(HttpServletRequest request) {
        Cookie cookie = CookieFactory.getCookieByName(request, "login");
        if (cookie != null) {
            return mainService.getByLogin(cookie.getValue());
        }
        return null;
    }

    @PostMapping(value = "/delete_cookies")
    public void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieFactory.deleteCookieByValue(request, response, "login");
    }
}
