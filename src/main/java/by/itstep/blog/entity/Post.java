package by.itstep.blog.entity;

import java.util.Objects;

public class Post {

    private long postId;
    private String title;
    private String description;
    private int rating;
    private String authorName;
    private int views;

    public Post() {}

    public Post(long postId, String title, String description, int rating, String authorName, int views) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.authorName = authorName;
        this.views = views;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Post post = new Post();

        public Builder setPostId(long postId) {
            post.postId = postId;
            return this;
        }

        public Builder setTitle(String title) {
            post.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            post.description = description;
            return this;
        }

        public Builder setRating(int rating) {
            post.rating = rating;
            return this;
        }

        public Builder setAuthorName(String authorName) {
            post.authorName = authorName;
            return this;
        }

        public Builder setViews(int views) {
            post.views = views;
            return this;
        }

        public Post build() {
            return post;
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Post post = (Post) o;
        return postId == post.postId && rating == post.rating && views == post.views && title.equals(post.title) && description.equals(post.description) && authorName.equals(post.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, title, description, rating, authorName, views);
    }


    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", authorName='" + authorName + '\'' +
                ", views=" + views +
                '}';
    }
}
