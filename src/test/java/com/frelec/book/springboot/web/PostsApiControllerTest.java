package com.frelec.book.springboot.web;

import com.frelec.book.springboot.domain.posts.Posts;
import com.frelec.book.springboot.domain.posts.PostsRepository;
import com.frelec.book.springboot.web.dto.PostsSaveRequestDto;
import com.frelec.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void RegisterPosts() throws Exception {
        // given
        final var title = "title";
        final var content = "content";
        final var author = "author";

        final var requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        final var url = "http://localhost:" + port + "/api/v1/posts";

        // when
        final var responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final var all = postsRepository.findAll();
        assertThat(all.getFirst().getTitle()).isEqualTo(title);
        assertThat(all.getFirst().getContent()).isEqualTo(content);
        assertThat(all.getFirst().getAuthor()).isEqualTo(author);
    }

    @Test
    public void UpdatePosts() {
        //given
        final var savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        final var updatedId = savedPosts.getId();
        final var expectedTitle = "title updated";
        final var expectedContent = "content updated";

        final var requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        final var url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        final var requestEntity = new HttpEntity<>(requestDto);

        // when
        final var responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final var all = postsRepository.findAll();
        assertThat(all.getFirst().getTitle()).isEqualTo(expectedTitle);
        assertThat(all.getFirst().getContent()).isEqualTo(expectedContent);
    }
}
