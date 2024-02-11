package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final UserService userService;

    public PostPage(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String postGet(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        Post post = postService.findById(id);
        if (post == null) {
            putMessage(httpSession, "Invalid post");
            return "redirect:/";
        }
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String postPost(@PathVariable("id") Long id, @Valid @ModelAttribute("comment") Comment comment,
                           BindingResult bindingResult, Model model, HttpSession httpSession) {
        Post post = postService.findById(id);
        if (post == null) {
            putMessage(httpSession, "Invalid post");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }
//        List<String> formatted = Arrays.stream(comment.getText()
//                .split(System.getProperty("line.separator")))
//                .map(s -> "<p>" + s + "</p>")
//                .collect(Collectors.toList());
//        StringBuilder sb = new StringBuilder();
//        for (String item : formatted) sb.append(item);
//        comment.setText(sb.toString());
//        String formatted = comment.getText().replaceAll(
//                System.getProperty("line.separator"),
//                "<br/>" + System.getProperty("line.separator"));
//        comment.setText(formatted);
        userService.writeComment(getUser(httpSession), post, comment);
        putMessage(httpSession, "New comment was published successfully");
        return "redirect:/post/" + id;
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handle(HttpSession httpSession) {
        putMessage(httpSession, "Invalid post");
        return "redirect:/";
    }
}
