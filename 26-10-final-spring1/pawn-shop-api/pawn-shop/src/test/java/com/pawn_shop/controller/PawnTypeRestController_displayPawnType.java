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
public class PawnTypeRestController_displayPawnType {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void displayPawnType_5() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnTypeRest/list/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void displayPawnItem_6() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/pawnTypeRest/list"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
