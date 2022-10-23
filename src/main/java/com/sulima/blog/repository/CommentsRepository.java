package com.sulima.blog.repository;

import com.sulima.blog.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    List<Comments> findAllByArticleId(Integer id);

}
