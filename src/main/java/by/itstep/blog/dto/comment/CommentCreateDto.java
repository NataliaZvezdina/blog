package by.itstep.blog.dto.comment;

import java.util.Objects;

public class CommentCreateDto {

    private long postId;
    private String message;
    private String authorName;

    public CommentCreateDto() {}

    public CommentCreateDto(long postId, String message, String authorName) {
        this.postId = postId;
        this.message = message;
        this.authorName = authorName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentCreateDto that = (CommentCreateDto) o;
        return postId == that.postId && message.equals(that.message) && authorName.equals(that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, message, authorName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentCreateDto{");
        sb.append("postId=").append(postId);
        sb.append(", message='").append(message).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
