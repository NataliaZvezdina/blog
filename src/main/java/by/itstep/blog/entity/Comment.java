package by.itstep.blog.entity;

import java.sql.Date;
import java.util.Objects;

public class Comment {

    private long commentId;
    private long postId;
    private String message;
    private String authorName;
    private int rating;
    private Date createdAt;

    public Comment() {}

    public Comment(long commentId, long postId, String message, String authorName, int rating, Date createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.message = message;
        this.authorName = authorName;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Comment comment = new Comment();

        public Builder setCommentId(long commentId) {
            comment.commentId = commentId;
            return this;
        }

        public Builder setPostId(long postId) {
            comment.postId = postId;
            return this;
        }

        public Builder setMessage(String message) {
            comment.message = message;
            return this;
        }

        public Builder setAuthorName(String authorName) {
            comment.authorName = authorName;
            return this;
        }

        public Builder setRating(int rating) {
            comment.rating = rating;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            comment.createdAt = createdAt;
            return this;
        }

        public Comment build() {
            return comment;
        }
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && postId == comment.postId && rating == comment.rating && message.equals(comment.message) && authorName.equals(comment.authorName) && createdAt.equals(comment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId, message, authorName, rating, createdAt);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", message='" + message + '\'' +
                ", authorName='" + authorName + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                '}';
    }
}
