package by.itstep.blog.service.impl;

import by.itstep.blog.dto.post.PostCreateDto;
import by.itstep.blog.dto.post.PostFullDto;
import by.itstep.blog.dto.post.PostPreviewDto;
import by.itstep.blog.dto.statistics.StatisticsDto;
import by.itstep.blog.entity.Comment;
import by.itstep.blog.entity.Post;
import by.itstep.blog.mapper.PostMapper;
import by.itstep.blog.repository.impl.CommentRepositoryImpl;
import by.itstep.blog.repository.impl.PostRepositoryImpl;
import by.itstep.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepositoryImpl postRepository;
    private CommentRepositoryImpl commentRepository;
    private PostMapper postMapper = new PostMapper();
    private List<PostPreviewDto> searchResult = new ArrayList<>();

    @Autowired
    public PostServiceImpl(PostRepositoryImpl postRepository, CommentRepositoryImpl commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostFullDto findById(long postId) {
        return postMapper.mapToFullDto(postRepository.findById(postId));
    }

    @Override
    public List<PostPreviewDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostPreviewDto> postPreviewDtos = postMapper.mapToDtoList(posts);
        return postPreviewDtos;
    }

    @Override
    public List<PostPreviewDto> findAll(int page) {
        List<Post> posts = postRepository.findAll(page);
        List<PostPreviewDto> postPreviewDtos = postMapper.mapToDtoList(posts);
        return postPreviewDtos;
    }

    @Override
    public void createPost(PostCreateDto postCreateDto) {
        Post postToCreate = postMapper.mapToEntity(postCreateDto);
        postRepository.create(postToCreate);
    }

    @Override
    public List<PostPreviewDto> findAllOrderedByRating(int page) {
        List<Post> posts = postRepository.findAllOrderedByRating(page);
        List<PostPreviewDto> postPreviewDtos = postMapper.mapToDtoList(posts);
        return postPreviewDtos;
    }

    @Override
    public List<PostPreviewDto> findAllOrderedByComments(int page) {
        List<Post> posts = postRepository.findAllOrderedByComments(page);
        List<PostPreviewDto> postPreviewDtos = postMapper.mapToDtoList(posts);
        return postPreviewDtos;
    }

    @Override
    public void upRating(long postId) {
        Post postToUpdate = postRepository.findById(postId);
        postToUpdate.setRating(postToUpdate.getRating() + 1);
        postRepository.update(postToUpdate);
    }

    @Override
    public void downRating(long postId) {
        Post postToUpdate = postRepository.findById(postId);
        postToUpdate.setRating(postToUpdate.getRating() - 1);
        postRepository.update(postToUpdate);
    }

    @Override
    public void upViews(long postId) {
        Post postToUpdate = postRepository.findById(postId);
        postToUpdate.setViews(postToUpdate.getViews() + 1);
        postRepository.update(postToUpdate);
    }

    @Override
    public void search(String text) {
        searchResult = postRepository.findAll().stream()
                .filter(p -> p.getTitle().contains(text))
                .map(p -> postMapper.mapToDto(p))
                .collect(Collectors.toList());
    }

    public List<PostPreviewDto> getSearchResult() {
        return searchResult;
    }

    @Override
    public void delete(long postId) {
        List<Comment> commentsToDelete = commentRepository.findAllByPostId(postId);
        System.out.println(commentsToDelete);
        commentsToDelete.forEach(c -> commentRepository.deleteById(c.getCommentId()));

        postRepository.deleteById(postId);
    }

    @Override
    public List<StatisticsDto> getStatistics() {
        return postRepository.collectStatistics();
    }
}
