package com.sulima.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя не заполнено.")
    @Size(min = 4, message = "Имя. Минимум 4 знака.")
    private String name;

    @NotBlank(message = "Логин не заполнен.")
    @Size(min = 4, message = "Логин. Минимум 4 знака.")
    private String login;

    @NotBlank(message = "Email не заполнен.")
    @Email(message = "Email. Не корректный.")
    private String email;

    @NotBlank(message = "Пароль. Не заполнен.")
    private String pass;
}
