package by.itstep.blog.controller;

import by.itstep.blog.dto.post.PostFullDto;
import by.itstep.blog.entity.Comment;
import by.itstep.blog.service.CommentService;
import by.itstep.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable long id) {
        Comment commentToDelete = commentService.findById(id);

        PostFullDto post = postService.findById(commentToDelete.getPostId());
        long postId = post.getPostId();
        commentService.deleteComment(id);
        return "redirect:/single-post/" + postId;
    }

}
