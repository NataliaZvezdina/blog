package by.itstep.blog.repository;

import by.itstep.blog.entity.Post;

import java.util.List;

public interface PostRepository {

    void create(Post post);

    Post findById(long postId);

    List<Post> findAll();

    List<Post> findAll(int page);

    void update(Post post);

    void deleteById(long postId);
}
