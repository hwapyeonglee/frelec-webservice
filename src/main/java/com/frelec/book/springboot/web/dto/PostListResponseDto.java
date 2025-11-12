package com.frelec.book.springboot.web.dto;

import com.frelec.book.springboot.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public static PostListResponseDto fromModel(Posts model) {
        return PostListResponseDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .author(model.getAuthor())
                .modifiedDate(model.getModifiedDate())
                .build();
    }
}
