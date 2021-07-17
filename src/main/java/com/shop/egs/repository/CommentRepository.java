package com.shop.egs.repository;

import com.shop.egs.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findAllByProductProductNameContains(String productName);
}
