package com.pawn_shop;

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
public class PawnShopRestController_deleteCustomer {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Creator: NghiaNVT
     * Date created: 17/10/2022
     * Function: delete_25
     * Description: In situation the id send value is null
     */
    @Test
    void deleteCustomerById_25_idNull() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/employee/customer/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Creator: NghiaNVT
     * Date created: 17/10/2022
     * Function: delete_26
     * Description: In situation the id send value is empty
     */
    @Test
    void deleteCustomerById_26_idEmpty() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/employee/customer/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Creator: NghiaNVT
     * Date created: 17/10/2022
     * Function: delete_27
     * Description: In situation the id send value was not founded in database
     */
    @Test
    void deleteCustomerById_27_idNotExist() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/employee/customer/99"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Creator: NghiaNVT
     * Date created: 17/10/2022
     * Function: delete_28
     * Description: In situation the id send value was founded in database
     */
    @Test
    void deleteCustomerById_28_idExist() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/employee/customer/4"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
