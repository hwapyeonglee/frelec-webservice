package com.frelec.book.springboot.web;

import com.frelec.book.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String savePosts() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePosts(@PathVariable Long id,
                              Model model) {
        final var dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
