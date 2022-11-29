package com.pawn_shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FinanceRestController_displayFinance {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void displayFinance_5() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("api/admin/finance/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void displayFinance_6() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("api/admin/finance/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
