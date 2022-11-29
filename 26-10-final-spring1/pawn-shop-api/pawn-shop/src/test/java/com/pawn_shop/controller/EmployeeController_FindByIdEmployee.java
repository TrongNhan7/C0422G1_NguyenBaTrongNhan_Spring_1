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
public class EmployeeController_FindByIdEmployee {
    @Autowired
    private MockMvc mockMvc;
    /**
     * Create by HoanTV
     * Date: 10:59 pm  17-10-2022
     * function: check find by id equals null
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void findById_id_1_null() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/employee/list/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by HoanTV
     * Date: 10:59 pm  17-10-2022
     * function: check find by id equals is empty
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void findById_id_2_isEmpy() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/employee/list/ "))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by HoanTV
     * Date: 10:59 pm  17-10-2022
     * function: check find by id no db
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void findById_id_3_NotExist() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/employee/list/345335"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
