package com.frelec.book.springboot.web;

import com.frelec.book.springboot.service.PostsService;
import com.frelec.book.springboot.web.dto.PostsResponseDto;
import com.frelec.book.springboot.web.dto.PostsSaveRequestDto;
import com.frelec.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<Long> save(@RequestBody PostsSaveRequestDto requestDto) {
        final var body = postsService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody PostsUpdateRequestDto requestDto) {
        final var body = postsService.update(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostsResponseDto> findById(@PathVariable Long id) {
        final var body = postsService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
