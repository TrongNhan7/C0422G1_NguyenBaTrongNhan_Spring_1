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
public class InterestRestController_findLiquidationContractByDate {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLiquidationContractByDate_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/liquidation-contract"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_Notfound() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/liquidation-contract/page=10"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/liquidation-contract?page=0&startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("content[0].id").value(8))
                .andExpect(jsonPath("content[0].code").value("HD-NV005-8"))
                .andExpect(jsonPath("content[0].itemPrice").value(2500000))
                .andExpect(jsonPath("content[0].interestRate").value(0.35))
                .andExpect(jsonPath("content[0].startDate").value("2022-07-03"))
                .andExpect(jsonPath("content[0].endDate").value("2022-10-13"))
                .andExpect(jsonPath("content[0].returnDate").value("2022-10-25"))
                .andExpect(jsonPath("content[0].liquidationPrice").value(3000000))
                .andExpect(jsonPath("content[0].customer.id").value(8))
                .andExpect(jsonPath("content[0].employee.id").value(5))
                .andExpect(jsonPath("content[0].pawnItem.id").value(8));

    }

    @Test
    public void getLiquidationContractByDate_startReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?startReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_startReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?startReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_endReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?endReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_endReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?endReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getLiquidationContractByDate_starReturnDate_And_endReturnDate_TestLogic() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?page=0&startReturnDate=2022-01-13&endReturnDate=2022-01-12"))
                .andDo(print())
                .andExpect(status().is(204));
    }

    @Test
    public void getLiquidationContractByDate_starReturnDate_And_endReturnDate_ok() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?page=0&startReturnDate=2022-06-13&endReturnDate=2022-10-12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getLiquidationContractByDate_starReturnDate_And_endReturnDate_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/liquidation-contract?startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("content[0].id").value(8))
                .andExpect(jsonPath("content[0].code").value("HD-NV005-8"))
                .andExpect(jsonPath("content[0].itemPrice").value(2500000))
                .andExpect(jsonPath("content[0].interestRate").value(0.35))
                .andExpect(jsonPath("content[0].startDate").value("2022-07-03"))
                .andExpect(jsonPath("content[0].endDate").value("2022-10-13"))
                .andExpect(jsonPath("content[0].returnDate").value("2022-10-25"))
                .andExpect(jsonPath("content[0].liquidationPrice").value(3000000))
                .andExpect(jsonPath("content[0].customer.id").value(8))
                .andExpect(jsonPath("content[0].employee.id").value(5))
                .andExpect(jsonPath("content[0].pawnItem.id").value(8));
    }


}
