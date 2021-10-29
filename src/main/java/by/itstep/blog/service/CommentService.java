package by.itstep.blog.service;

import by.itstep.blog.dto.comment.CommentCreateDto;
import by.itstep.blog.dto.comment.CommentFullDto;
import by.itstep.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(long commentId);

    List<CommentFullDto> findAllByPostId(long postId);

    void create(CommentCreateDto comment);

    void upRating(long commentId);

    void downRating(long commentId);

    void deleteComment(long commentId);
}
