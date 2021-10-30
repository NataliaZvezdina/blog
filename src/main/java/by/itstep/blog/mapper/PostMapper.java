package by.itstep.blog.mapper;

import by.itstep.blog.dto.post.PostCreateDto;
import by.itstep.blog.dto.post.PostFullDto;
import by.itstep.blog.dto.post.PostPreviewDto;
import by.itstep.blog.entity.Post;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public Post extract(ResultSet rs) throws SQLException {
        return Post.getBuilder()
                .setPostId(rs.getLong("post_id"))
                .setTitle(rs.getString("title"))
                .setDescription(rs.getString("description"))
                .setRating(rs.getInt("rating"))
                .setAuthorName(rs.getString("author_name"))
                .setViews(rs.getInt("views"))
                .setCreatedAt(rs.getDate("created_at"))
                .build();
    }

    public PostPreviewDto mapToDto(Post post) {
        PostPreviewDto postPreviewDto = new PostPreviewDto();
        postPreviewDto.setPostId(post.getPostId());
        postPreviewDto.setTitle(post.getTitle());
        postPreviewDto.setRating(post.getRating());
        postPreviewDto.setAuthorName(post.getAuthorName());
        postPreviewDto.setViews(post.getViews());
        return postPreviewDto;
    }

    public List<PostPreviewDto> mapToDtoList(List<Post> posts) {
        return posts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Post mapToEntity(PostCreateDto postCreateDto) {
        return Post.getBuilder()
                .setTitle(postCreateDto.getTitle())
                .setDescription(postCreateDto.getDescription())
                .setAuthorName(postCreateDto.getAuthorName())
                .setCreatedAt(Date.valueOf(LocalDate.now()))
                .build();
    }

    public PostFullDto mapToFullDto(Post post) {
        return PostFullDto.getBuilder()
                .setPostId(post.getPostId())
                .setAuthorName(post.getAuthorName())
                .setDescription(post.getDescription())
                .setTitle(post.getTitle())
                .setRating(post.getRating())
                .build();
    }
}
