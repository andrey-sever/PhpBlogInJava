package com.sulima.blog.data;

import com.sulima.blog.model.Users;
import com.sulima.blog.repository.UsersRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public class MainService {

    private final UsersRepository usersRepository;
    private final String HASH_PLUS = "10zM92La3874QIUw56";

    public MainService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(Users user) {
        user.setPass(getPasswordMd5(user.getPass()));
        usersRepository.save(user);
    }

    public String getError(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "1";
        }
        ObjectError firstError = bindingResult.getAllErrors().get(0);
        return "Ошибка. ".concat(firstError.getDefaultMessage());
    }

    public String getErrorPass(String login, String pass) {

        if (login.trim() == "") {
            return "Не введен логин.";
        } else if (pass.trim() == "") {
            return "Не введен пароль.";
        } else if (!usersRepository.existsByLoginAndPass(login, getPasswordMd5(pass))){
            return "Пользователь не найден.";
        }
        return "1";
    }

    public Users getByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    private String getPasswordMd5(String pass) {
        return DigestUtils.md5Hex(pass.concat(HASH_PLUS)).toUpperCase();
    }
}
