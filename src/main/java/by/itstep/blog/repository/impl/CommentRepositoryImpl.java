package by.itstep.blog.repository.impl;

import by.itstep.blog.entity.Comment;
import by.itstep.blog.mapper.CommentMapper;
import by.itstep.blog.repository.CommentRepository;
import by.itstep.blog.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private CommentMapper commentMapper = new CommentMapper();

    private static final String INSERT_QUERY = "INSERT INTO comment (post_id, message, author_name, rating, " +
                                                     "created_at) " +
                                                "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * " +
                                             "FROM comment " +
                                             "WHERE comment_id = ?;";

    private static final String FIND_ALL_QUERY = "SELECT * " +
                                                 "FROM comment;";

    private static final String UPDATE_QUERY = "UPDATE comment " +
            "SET post_id = ?, message = ?, author_name = ?, rating = ?, created_at = ? " +
            "WHERE comment_id = ?;";

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM comment " +
            "WHERE comment_id = ?;";

    private static final String FIND_ALL_BY_POST_ID_QUERY = "SELECT * FROM comment " +
            "WHERE post_id = ?;";

    @Override
    public void create(Comment comment) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {

            pstmt.setLong(1, comment.getPostId());
            pstmt.setString(2, comment.getMessage());
            pstmt.setString(3, comment.getAuthorName());
            pstmt.setInt(4, comment.getRating());
            pstmt.setDate(5, comment.getCreatedAt());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating comment", e);
        }
    }

    @Override
    public Comment findById(long commentId) {
        try (Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID)) {

            pstmt.setLong(1, commentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                Comment found = null;
                if (rs.next()) {
                    found = commentMapper.extract(rs);
                }
                return found;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting comment", e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try (Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = pstmt.executeQuery()) {

            List<Comment> foundComments = new ArrayList<>();
            while (rs.next()) {
                Comment found = commentMapper.extract(rs);
                foundComments.add(found);
            }
            return foundComments;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting comments", e);
        }
    }

    @Override
    public List<Comment> findAllByPostId(long postId) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_ALL_BY_POST_ID_QUERY)) {

            pstmt.setLong(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {
                List<Comment> foundComments = new ArrayList<>();
                while (rs.next()) {
                    Comment found = commentMapper.extract(rs);
                    foundComments.add(found);
                }
                return foundComments;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting comments", e);
        }
    }

    @Override
    public void update(Comment comment) {
        try (Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {

            pstmt.setLong(1, comment.getPostId());
            pstmt.setString(2, comment.getMessage());
            pstmt.setString(3, comment.getAuthorName());
            pstmt.setInt(4, comment.getRating());
            pstmt.setDate(5, comment.getCreatedAt());
            pstmt.setLong(6, comment.getCommentId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while updating comment", e);
        }
    }

    @Override
    public void deleteById(long commentId) {
        try (Connection con = ConnectionUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID_QUERY)) {

            pstmt.setLong(1, commentId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while deleting comment", e);
        }
    }
}
