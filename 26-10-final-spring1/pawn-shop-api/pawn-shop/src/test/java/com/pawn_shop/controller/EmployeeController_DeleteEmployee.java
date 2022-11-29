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
public class EmployeeController_DeleteEmployee {
    @Autowired
    private MockMvc mockMvc;
    /**
     * Create by: HoanTV
     * Date create: 17/10/2022
     * Function: delete Id = null
     * @throws Exception
     */
    @Test
    public void deleteEmployee_idNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/employee/delete/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: HoanTV
     * Date create: 17/10/2022
     * Function: delete Id = is empty
     * @throws Exception
     */
    @Test
    public void deleteEmployee_idIsEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/employee/delete/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: HoanTV
     * Date create: 17/10/2022
     * Function: delete Id = Not Exist
     * @throws Exception
     */
    @Test
    public void deleteEmployee_idNotExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/employee/delete/63450"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: HoanTV
     * Date create: 17/10/2022
     * Function: delete Id = wrong format
     * @throws Exception
     */
    @Test
    public void deleteEmployee_idWrongFormat() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/employee/delete/@@@@"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: HoanTV
     * Date create: 17/10/2022
     * Function: delete Id = ok
     * @throws Exception
     */
    @Test
    public void deleteEmployee_id() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/employee/delete/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
