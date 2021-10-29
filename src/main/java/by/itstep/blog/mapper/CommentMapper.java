package by.itstep.blog.mapper;

import by.itstep.blog.dto.comment.CommentCreateDto;
import by.itstep.blog.dto.comment.CommentFullDto;
import by.itstep.blog.entity.Comment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public Comment extract(ResultSet rs) throws SQLException {
        return Comment.getBuilder()
                .setCommentId(rs.getLong("comment_id"))
                .setPostId(rs.getLong("post_id"))
                .setMessage(rs.getString("message"))
                .setAuthorName(rs.getString("author_name"))
                .setRating(rs.getInt("rating"))
                .setCreatedAt(rs.getDate("created_at"))
                .build();
    }

    public CommentFullDto mapToFullDto(Comment comment) {
        return CommentFullDto.getBuilder()
                .setCommentId(comment.getCommentId())
                .setMessage(comment.getMessage())
                .setAuthorName(comment.getAuthorName())
                .setPostId(comment.getPostId())
                .setCreatedAt(comment.getCreatedAt())
                .setRating(comment.getRating())
                .build();
    }

    public List<CommentFullDto> mapToDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::mapToFullDto)
                .collect(Collectors.toList());
    }

    public Comment mapToEntity(CommentCreateDto comment) {
        return Comment.getBuilder()
                .setPostId(comment.getPostId())
                .setAuthorName(comment.getAuthorName())
                .setMessage(comment.getMessage())
                .setRating(0)
                .setCreatedAt(Date.valueOf(LocalDate.now()))
                .build();
    }
}
