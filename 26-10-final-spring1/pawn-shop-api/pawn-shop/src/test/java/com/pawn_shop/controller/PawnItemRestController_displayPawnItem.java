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
public class PawnItemRestController_displayPawnItem {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void displayPawnItem_5() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnItemRest?page=2&itemName=&pawnName="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[3].name").value("Yamaha Exciter"))
                .andExpect(jsonPath("content[3].pawnType.id").value("Xe Máy"))
                .andExpect(jsonPath("content[3].status").value(false))
                .andExpect(jsonPath("content[3].endDate").value("2022-05-15"))
                .andExpect(jsonPath("content[3].itemPrice").value(12000000));
    }

    @Test
    public void displayPawnItem_6() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnItemRest?page=&itemName=Máy Ảnh&pawnName="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[2].name").value("Máy ảnh Cannon EOS 1500D"))
                .andExpect(jsonPath("content[2].pawnType.id").value("Máy Ảnh"))
                .andExpect(jsonPath("content[2].status").value(true))
                .andExpect(jsonPath("content[2].endDate").value("2022-10-13"))
                .andExpect(jsonPath("content[2].itemPrice").value(2500000));
    }

    @Test
    public void displayPawnItem_7() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnItemRest?page=&itemName=&pawnName=IPhone 13 ProMax"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[2].name").value("IPhone 13 ProMax"))
                .andExpect(jsonPath("content[2].pawnType.id").value("Điện Thoại"))
                .andExpect(jsonPath("content[2].status").value(true))
                .andExpect(jsonPath("content[2].endDate").value("2022-08-02"))
                .andExpect(jsonPath("content[2].itemPrice").value(2200000));
    }

    @Test
    public void displayPawnItem_8() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnItemRest?page=&itemName=Xe Máy&pawnName=Honda Air Balade"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[2].name").value("Honda Air Balade"))
                .andExpect(jsonPath("content[2].pawnType.id").value("Xe Máy"))
                .andExpect(jsonPath("content[2].status").value(false))
                .andExpect(jsonPath("content[2].endDate").value("2022-10-13"))
                .andExpect(jsonPath("content[2].itemPrice").value(13000000));
    }

    @Test
    public void displayPawnItem_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnItemRest/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
