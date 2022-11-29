package com.pawn_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class LiquidationRestController_findByNameCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByNameCustomer_4
     * Description: In situation with input [name] send the value is exist
     */
    @Test
    void findByNameCustomer_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/list/n"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].name").value("Trọng Nghĩa"))
                .andExpect(jsonPath("content[0].code").value("KH-1"))
                .andExpect(jsonPath("content[0].idCard").value("20117440"));
    }

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByNameCustomer_3
     * Description: In situation with input [name] send the value is empty
     */
    @Test
    public void findByNameCustomer_3() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/list/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByNameCustomer_1
     * Description: In situation with input [name] send the value is null
     */
    @Test
    void findByNameCustomer_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/list/null"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].name").value("Trọng Nghĩa"))
                .andExpect(jsonPath("content[0].code").value("KH-1"))
                .andExpect(jsonPath("content[0].idCard").value("20117440"));
    }
}
