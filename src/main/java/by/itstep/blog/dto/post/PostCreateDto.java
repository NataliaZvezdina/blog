package by.itstep.blog.dto.post;

import java.util.Objects;

public class PostCreateDto {

    private String title;
    private String description;
    private String authorName;

    public PostCreateDto() {}

    public PostCreateDto(String title, String description, String authorName) {
        this.title = title;
        this.description = description;
        this.authorName = authorName;
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
        PostCreateDto that = (PostCreateDto) o;
        return title.equals(that.title) && description.equals(that.description) && authorName.equals(that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, authorName);
    }

    @Override
    public String toString() {
        return "PostCreateDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
