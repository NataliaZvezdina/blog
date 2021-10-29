package by.itstep.blog.dto.post;

import java.util.Objects;

public class PostPreviewDto {

    private long postId;
    private String title;
    private int rating;
    private String authorName;
    private int views;

    public PostPreviewDto() {}

    public PostPreviewDto(long postId, String title, int rating, String authorName, int views) {
        this.postId = postId;
        this.title = title;
        this.rating = rating;
        this.authorName = authorName;
        this.views = views;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostPreviewDto that = (PostPreviewDto) o;
        return postId == that.postId && rating == that.rating && views == that.views && title.equals(that.title) && authorName.equals(that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, title, rating, authorName, views);
    }

    @Override
    public String toString() {
        return "PostPreviewDto{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", authorName='" + authorName + '\'' +
                ", views=" + views +
                '}';
    }
}
