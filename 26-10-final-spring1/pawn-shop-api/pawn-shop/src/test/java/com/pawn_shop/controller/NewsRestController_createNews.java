package com.pawn_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawn_shop.dto.NewsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsRestController_createNews {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createNews_title_13() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle(null);
        newsDto.setContent("Content");
        newsDto.setPostingDay("2000-10-05");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void createNews_title_14() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("");
        newsDto.setContent("Content");
        newsDto.setPostingDay("2000-10-05");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_content_13() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent(null);
        newsDto.setPostingDay("2000-10-05");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void createNews_content_14() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("");
        newsDto.setPostingDay("2000-10-05");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_postingDay_13() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay(null);
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_postingDay_14() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay("");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_postingDay_15() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay("ssssss");
        newsDto.setImgUrl("f");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_img_13() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay("2022-12-12");
        newsDto.setImgUrl(null);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_img_14() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay("2022-12-12");
        newsDto.setImgUrl("");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createNews_18() throws Exception {

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("title");
        newsDto.setContent("content");
        newsDto.setPostingDay("2022-10-17");
        newsDto.setImgUrl("co img");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/news/create")
                        .content(this.objectMapper.writeValueAsString(newsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
