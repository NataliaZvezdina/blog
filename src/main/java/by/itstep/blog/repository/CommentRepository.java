package by.itstep.blog.repository;

import by.itstep.blog.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void create(Comment comment);

    Comment findById(long commentId);

    List<Comment> findAll();

    List<Comment> findAllByPostId(long postId);

    void update(Comment comment);

    void deleteById(long commentId);
}
