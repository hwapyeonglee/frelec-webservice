package com.frelec.book.springboot.service;

import com.frelec.book.springboot.domain.posts.PostsRepository;
import com.frelec.book.springboot.web.dto.PostsResponseDto;
import com.frelec.book.springboot.web.dto.PostsSaveRequestDto;
import com.frelec.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id,PostsUpdateRequestDto requestDto) {
        final var posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No posts. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return posts.getId();
    }

    public PostsResponseDto findById(Long id) {
        final var posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No posts. id=" + id));
        return new PostsResponseDto(posts);
    }
}
