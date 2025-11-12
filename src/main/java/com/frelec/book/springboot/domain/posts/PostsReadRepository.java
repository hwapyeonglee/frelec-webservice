package com.frelec.book.springboot.domain.posts;

import java.util.List;

public interface PostsReadRepository {
    List<Posts> findAllDesc();
}
