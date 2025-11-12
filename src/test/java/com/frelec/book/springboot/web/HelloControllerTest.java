package com.frelec.book.springboot.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = HelloController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("/hello returns 'hello'")
    void returnsHello() throws Exception {
        mvc.perform(get("/hello"))
            .andExpectAll(
                status().isOk(),
                content().string("hello")
            );
    }

    @Test
    @DisplayName("/hello/dto returns HelloResponseDto")
    void returnsHelloResponseDto() throws Exception {
        final var name = "hello";
        final var amount = 1000;
        mvc.perform(get("/hello/dto")
            .param("name", name)
            .param("amount", String.valueOf(amount)))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.name", is(name)),
                jsonPath("$.amount", is(amount))
            );
    }
}
