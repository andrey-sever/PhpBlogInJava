package com.sulima.blog.repository;

import com.sulima.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByLoginAndPass(String login, String pass);

    Users findByLogin(String login);
}
