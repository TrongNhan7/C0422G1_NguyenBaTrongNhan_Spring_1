package com.pawn_shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.xml.bind.annotation.XmlElementRefs;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsRestController_getListNews {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllNews_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/list-news")
        ).andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void getAllNews_6() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/list-news?page=0")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[3].title").value("Cầm xe ô tô BMW Model X3, X4, X5, X7 330i, 340i, 730i, 750i"))
                .andExpect(jsonPath("content[3].content").value("Ô tô là một trong những phương tiện được cầm cố nhiều, bởi đây là tài sản lớn và dễ quy đổi thanh khoản vay. Nếu bạn đang gặp vấn đề về tài chính, cần hỗ trợ khoản tiền lớn và đồng thời đang sở hữu xe ô tô BMW, việc cầm xe ô tô"))
                .andExpect(jsonPath("content[3].postingDay").value("2022-10-10"));
    }
    @Test
    public void getAllNews_title_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/list-news?title=null")
                ).andDo(print())
                .andExpect(status().is(404));
    }
    @Test
    public void getAllNews_title_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/list-news?title= ")
                ).andDo(print())
                .andExpect(status().is(204));
    }
    @Test
    public void getAllNews_title_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/list-news?tile=ádsa")
                ).andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getAllNews_title_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/list-news?tile=Cầm Xe Ô Tô Hạng Sang Siêu Xe")
                ).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }




}