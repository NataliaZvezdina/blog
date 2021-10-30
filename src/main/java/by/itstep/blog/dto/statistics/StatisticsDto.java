package by.itstep.blog.dto.statistics;

import java.sql.Date;
import java.util.Objects;

public class StatisticsDto {

    private Date date;
    private int counterPosts;
    private int counterComments;

    public StatisticsDto() {}

    public StatisticsDto(Date date, int counterPosts, int counterComments) {
        this.date = date;
        this.counterPosts = counterPosts;
        this.counterComments = counterComments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCounterPosts() {
        return counterPosts;
    }

    public void setCounterPosts(int counterPosts) {
        this.counterPosts = counterPosts;
    }

    public int getCounterComments() {
        return counterComments;
    }

    public void setCounterComments(int counterComments) {
        this.counterComments = counterComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsDto that = (StatisticsDto) o;
        return counterPosts == that.counterPosts &&
                counterComments == that.counterComments &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, counterPosts, counterComments);
    }

    @Override
    public String toString() {
        return "StatisticsDto{" +
                "date=" + date +
                ", counterPosts=" + counterPosts +
                ", counterComments=" + counterComments +
                '}';
    }
}
