package by.itstep.blog.service;

import by.itstep.blog.dto.post.PostCreateDto;
import by.itstep.blog.dto.post.PostFullDto;
import by.itstep.blog.dto.post.PostPreviewDto;
import by.itstep.blog.dto.statistics.StatisticsDto;
import by.itstep.blog.entity.Post;

import java.util.List;

public interface PostService {

    PostFullDto findById(long postId);

    List<PostPreviewDto> findAll();

    List<PostPreviewDto> findAll(int page);

    void createPost(PostCreateDto post);

    List<PostPreviewDto> findAllOrderedByRating(int page);

    List<PostPreviewDto> findAllOrderedByComments(int page);

    void upRating(long postId);

    void downRating(long postId);

    void upViews(long postId);

    void search(String text);

    List<PostPreviewDto> getSearchResult();

    void delete(long postId);

    List<StatisticsDto> getStatistics();
}
