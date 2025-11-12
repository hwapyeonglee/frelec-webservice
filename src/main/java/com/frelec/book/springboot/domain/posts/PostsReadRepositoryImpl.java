package com.frelec.book.springboot.domain.posts;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

@Repository
public class PostsReadRepositoryImpl implements PostsReadRepository {

    private final JPAQueryFactory query;

    public PostsReadRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findAllDesc() {
        return query.selectFrom(QPosts.posts)
                .orderBy(QPosts.posts.id.desc())
                .fetch();
    }
}