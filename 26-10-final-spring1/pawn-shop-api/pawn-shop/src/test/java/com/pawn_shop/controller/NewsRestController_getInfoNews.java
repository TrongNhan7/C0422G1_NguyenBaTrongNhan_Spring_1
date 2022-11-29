package com.pawn_shop.controller;

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
public class NewsRestController_getInfoNews {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInfoNews_id_1() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/findId/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getInfoNews_id_2() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/findId/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getInfoNews_id_3() throws  Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/findId/{id}",21)
        ).andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getInfoStudent_id_4() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/findId/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("Cầm Xe Ô Tô Hạng Sang Siêu Xe Cao Cấp Định Giá Cao Nhất"))
                .andExpect(jsonPath("content").value("Bạn đang sở hữu một hay vài chiếc xe ô tô hạng sang, siêu xe và băn khoăn giữa vô số các đơn vị cung cấp dịch vụ cầm đồ uy tín lãi suất thấp, hãy cùng điểm qua ngay dịch vụ nhận cầm xe ô tô hạng sang, siêu xe tại hệ thống cầm"))
                .andExpect(jsonPath("postingDay").value("2022-10-11"))
                .andExpect(jsonPath("imgUrl").value("image"))
                .andExpect(jsonPath("status").value(true))
                .andExpect(jsonPath("appUser.id").value(1));
    }
}
