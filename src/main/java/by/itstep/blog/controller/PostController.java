package by.itstep.blog.controller;

import by.itstep.blog.dto.comment.CommentCreateDto;
import by.itstep.blog.dto.comment.CommentFullDto;
import by.itstep.blog.dto.post.PostCreateDto;
import by.itstep.blog.dto.post.PostFullDto;
import by.itstep.blog.dto.post.PostPreviewDto;
import by.itstep.blog.dto.statistics.StatisticsDto;
import by.itstep.blog.security.SecurityService;
import by.itstep.blog.service.CommentService;
import by.itstep.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/index")
    public String getMainPage(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        List<PostPreviewDto> previewDtoList = postService.findAll(page);
        String text = "";
        boolean loggedIn = securityService.isLoggedIn();
        model.addAttribute("previewDtoList", previewDtoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("text", text);
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }

    @GetMapping("/create-post")
    public String createPostForm(Model model) {
        PostCreateDto postToCreate = new PostCreateDto();
        model.addAttribute("postToCreate", postToCreate);
        return "create-post";
    }

    @PostMapping("/create-post")
    public String createProduct(PostCreateDto post) {
        postService.createPost(post);
        return "redirect:/index";
    }

    @GetMapping("/posts-ordered-by-rating")
    public String getPostsOrderedByRating(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        List<PostPreviewDto> previewDtoList = postService.findAllOrderedByRating(page);
        model.addAttribute("previewDtoList", previewDtoList);
        model.addAttribute("currentPage", page);
        return "posts-ordered-by-rating";
    }

    @GetMapping("/posts-ordered-by-comments")
    public String getPostsOrderedByComments(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        List<PostPreviewDto> previewDtoList = postService.findAllOrderedByComments(page);
        model.addAttribute("previewDtoList", previewDtoList);
        model.addAttribute("currentPage", page);
        return "posts-ordered-by-comments";
    }

    @GetMapping("/single-post/{id}")
    public String getSinglePost(Model model, @PathVariable long id) {
        postService.upViews(id);
        PostFullDto singlePost = postService.findById(id);
        List<CommentFullDto> comments = commentService.findAllByPostId(id);

        CommentCreateDto commentToCreate = new CommentCreateDto();

        boolean loggedIn = securityService.isLoggedIn();

        model.addAttribute("singlePost", singlePost);
        model.addAttribute("comments", comments);
        model.addAttribute("commentToCreate", commentToCreate);
        model.addAttribute("loggedIn", loggedIn);
        return "single-post";
    }

    @PostMapping("/create-comment")
    public String createComment(CommentCreateDto commentToCreate) {
        commentService.create(commentToCreate);
        long id = commentToCreate.getPostId();
        return "redirect:/single-post/" + id;
    }

    @GetMapping("/up-post")
    public String getUpPost(@RequestParam long postId) {
        postService.upRating(postId);
        return "redirect:/single-post/" + postId;
    }

    @GetMapping("/down-post")
    public String getDownPost(@RequestParam long postId) {
        postService.downRating(postId);
        return "redirect:/single-post/" + postId;
    }

    @GetMapping("/up-comment")
    public String getUpComment(@RequestParam long id) {
        commentService.upRating(id);
        long postId = commentService.findById(id).getPostId();
        return "redirect:/single-post/" + postId;
    }

    @GetMapping("/down-comment")
    public String detDownComment(@RequestParam long id) {
        commentService.downRating(id);
        long postId = commentService.findById(id).getPostId();
        return "redirect:/single-post/" + postId;
    }

    @PostMapping("/search")
    public String search(String text) {
        postService.search(text);
        return "redirect:/search-result";
    }

    @GetMapping("/search-result")
    public String getSearchResult(Model model) {
        List<PostPreviewDto> searchResult = postService.getSearchResult();
        model.addAttribute("previewDtoList", searchResult);
        return "search-result";
    }

    @GetMapping("/delete-post/{id}")
    public String deletePost(@PathVariable long id) {
        if (!securityService.isLoggedIn()) {
            return "redirect:/sign-in";
        }
        postService.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        if (!securityService.isLoggedIn()) {
            return "redirect:/sign-in";
        }
        List<StatisticsDto> statistics = postService.getStatistics();
        model.addAttribute("statistics", statistics);
        return "statistics";
    }
}
