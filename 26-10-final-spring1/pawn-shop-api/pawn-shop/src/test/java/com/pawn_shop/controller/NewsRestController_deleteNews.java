package com.pawn_shop.controller;

import com.pawn_shop.model.news.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsRestController_deleteNews {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteNews_id_25() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/deleteNews/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void deleteNews_id_26() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/deleteNews/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void deleteNews_id_27() throws  Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/deleteNews/{id}",21)
                ).andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void deleteNews_id_28() throws  Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/delete/1")
        ).andDo(print())
                .andExpect(status().is(200));
    }
}
