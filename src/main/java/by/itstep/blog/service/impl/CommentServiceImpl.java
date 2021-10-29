package by.itstep.blog.service.impl;

import by.itstep.blog.dto.comment.CommentCreateDto;
import by.itstep.blog.dto.comment.CommentFullDto;
import by.itstep.blog.entity.Comment;
import by.itstep.blog.mapper.CommentMapper;
import by.itstep.blog.repository.CommentRepository;
import by.itstep.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    private CommentMapper commentMapper = new CommentMapper();

    @Override
    public Comment findById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<CommentFullDto> findAllByPostId(long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentFullDto> commentFullDtos = commentMapper.mapToDtoList(comments);

        return commentFullDtos;
    }

    @Override
    public void create(CommentCreateDto comment) {
        commentRepository.create(commentMapper.mapToEntity(comment));
    }

    @Override
    public void upRating(long commentId) {
        Comment commentToUpdate = commentRepository.findById(commentId);
        commentToUpdate.setRating(commentToUpdate.getRating() + 1);
        commentRepository.update(commentToUpdate);
    }

    @Override
    public void downRating(long commentId) {
        Comment commentToUpdate = commentRepository.findById(commentId);
        commentToUpdate.setRating(commentToUpdate.getRating() - 1);
        commentRepository.update(commentToUpdate);
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
