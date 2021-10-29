package by.itstep.blog.repository.impl;

import by.itstep.blog.entity.Post;
import by.itstep.blog.mapper.PostMapper;
import by.itstep.blog.repository.PostRepository;
import by.itstep.blog.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private PostMapper postMapper = new PostMapper();

    private static final String INSERT_QUERY = "INSERT INTO post (title, description, rating, author_name, views) " +
                                               "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_BY_ID = "SELECT * " +
                                             "FROM post " +
                                             "WHERE post_id = ?;";

    private static final String FIND_ALL_QUERY = "SELECT * " +
                                                 "FROM post;";

    private static final String FIND_PAGE_QUERY = "SELECT * FROM post " +
            "LIMIT ?, ?;";

    private static final int NUM_OF_ELEMENTS = 3;

    private static final String UPDATE_QUERY = "UPDATE post " +
            "SET title = ?, description = ?, rating = ?, author_name = ?, views = ? " +
            "WHERE post_id = ?;";

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM post " +
            "WHERE post_id = ?;";

    private static final String FIND_PAGE_QUERY_ORDERED_BY_RATING = "SELECT * FROM post " +
            "ORDER BY rating desc " +
            "LIMIT ?, ?;";

    private static final String FIND_PAGE_QUERY_ORDERED_BY_COMMENTS = "SELECT *, COUNT(comment_id) AS 'num' " +
            "FROM post LEFT JOIN comment ON post.post_id = comment.post_id " +
            "GROUP BY post.post_id " +
            "ORDER BY num DESC " +
            "LIMIT ?, ?;";

    @Override
    public void create(Post post) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDescription());
            pstmt.setInt(3, post.getRating());
            pstmt.setString(4, post.getAuthorName());
            pstmt.setInt(5, post.getViews());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating post", e);
        }
    }

    @Override
    public Post findById(long postId) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID)) {

            pstmt.setLong(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {
                Post found = null;
                if (rs.next()) {
                    found = postMapper.extract(rs);
                }
                return found;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting post", e);
        }
    }

    @Override
    public List<Post> findAll() {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            List<Post> foundPosts = new ArrayList<>();
            while (rs.next()) {
                Post found = postMapper.extract(rs);
                foundPosts.add(found);
            }
            return foundPosts;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting posts", e);
        }

    }

    @Override
    public List<Post> findAll(int page) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_PAGE_QUERY)) {

            pstmt.setInt(1, NUM_OF_ELEMENTS * (page - 1));
            pstmt.setInt(2, NUM_OF_ELEMENTS);

            try(ResultSet rs = pstmt.executeQuery()) {
                List<Post> foundPosts = new ArrayList<>();
                while (rs.next()) {
                    Post found = postMapper.extract(rs);
                    foundPosts.add(found);
                }
                return foundPosts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting posts page " + page, e);
        }
    }

    @Override
    public void update(Post post) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDescription());
            pstmt.setInt(3, post.getRating());
            pstmt.setString(4, post.getAuthorName());
            pstmt.setInt(5, post.getViews());
            pstmt.setLong(6, post.getPostId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while updating post", e);
        }
    }

    @Override
    public void deleteById(long postId) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID_QUERY)) {

            pstmt.setLong(1, postId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while deleting post", e);
        }
    }

    public List<Post> findAllOrderedByRating(int page) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_PAGE_QUERY_ORDERED_BY_RATING)) {

            pstmt.setInt(1, NUM_OF_ELEMENTS * (page - 1));
            pstmt.setInt(2, NUM_OF_ELEMENTS);

            try(ResultSet rs = pstmt.executeQuery()) {
                List<Post> foundPosts = new ArrayList<>();
                while (rs.next()) {
                    Post found = postMapper.extract(rs);
                    foundPosts.add(found);
                }
                return foundPosts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting posts page " + page, e);
        }
    }

    public List<Post> findAllOrderedByComments(int page) {
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_PAGE_QUERY_ORDERED_BY_COMMENTS)) {

            pstmt.setInt(1, NUM_OF_ELEMENTS * (page - 1));
            pstmt.setInt(2, NUM_OF_ELEMENTS);

            try(ResultSet rs = pstmt.executeQuery()) {
                List<Post> foundPosts = new ArrayList<>();
                while (rs.next()) {
                    Post found = postMapper.extract(rs);
                    foundPosts.add(found);
                }
                return foundPosts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting posts page " + page, e);
        }
    }
}
