package com.pawn_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class LiquidationRestController_findByPawnType {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByPawnType_id_name_price_3
     * Description: In situation with input [id][name][price] does not exist in db
     */

    @Test
    public void findByPawnType_id_name_price_3() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/pawnType/list?id=?mane=?price=?"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByPawnType_id_name_price_1
     * Description: In situation with input [id][name][price] is null
     */

    @Test
    public void findByPawnType_id_name_price_1() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/pawnType/list?id=null?mane=null?price=null"))
                .andDo(print())
                .andExpect(status().is(204));
    }

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByPawnType_id_name_price_1
     * Description: In situation with input [id][name][price] is empty
     */

    @Test
    public void findByPawnType_id_name_price_2() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/pawnType/list?id= ?mane= ?price= "))
                .andDo(print())
                .andExpect(status().is(204));
    }

    /**
     * Creator: TriDM
     * Date created: 17-10-2022
     * Function: findByPawnType_id_name_price_4
     * Description: In situation with input [id][name][price] is exist
     */

    @Test
    public void findByPawnType_id_name_price_4() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/pawnType/list?id=1?mane=n?price=1"))
                .andDo(print())
                .andExpect(status().is(204));
    }
}
