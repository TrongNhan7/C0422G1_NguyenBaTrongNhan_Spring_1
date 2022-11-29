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
public class InterestRestController_findCompleteContractByDate {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCompleteContractByDate_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/complete-contract"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_NotFound() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/complete-contract/page=10"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/complete-contract?page=0&startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(3))
                .andExpect(jsonPath("content[0].id").value(5))
                .andExpect(jsonPath("content[0].code").value("HD-NV003-5"))
                .andExpect(jsonPath("content[0].itemPrice").value(99000000))
                .andExpect(jsonPath("content[0].startDate").value("2022-05-20"))
                .andExpect(jsonPath("content[0].endDate").value("2022-06-07"))
                .andExpect(jsonPath("content[0].returnDate").value("2022-06-07"))
                .andExpect(jsonPath("content[0].customer.id").value(5))
                .andExpect(jsonPath("content[0].employee.id").value(3))
                .andExpect(jsonPath("content[0].pawnItem.id").value(5));

    }

    @Test
    public void getCompleteContractByDate_startReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?startReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_startReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?startReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_endReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?endReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_endReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?endReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompleteContractByDate_starReturnDate_And_endReturnDate_TestLogic() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?page=0&startReturnDate=2022-01-13&endReturnDate=2022-01-12"))
                .andDo(print())
                .andExpect(status().is(204));
    }

    @Test
    public void getCompleteContractByDate_starReturnDate_And_endReturnDate_ok() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?page=0&startReturnDate=2022-01-13&endReturnDate=2022-10-12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getCompleteContractByDate_starReturnDate_And_endReturnDate_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/complete-contract?startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(3))
                .andExpect(jsonPath("content[0].id").value(5))
                .andExpect(jsonPath("content[0].code").value("HD-NV003-5"))
                .andExpect(jsonPath("content[0].itemPrice").value(99000000))
                .andExpect(jsonPath("content[0].startDate").value("2022-05-20"))
                .andExpect(jsonPath("content[0].endDate").value("2022-06-07"))
                .andExpect(jsonPath("content[0].returnDate").value("2022-06-07"))
                .andExpect(jsonPath("content[0].customer.id").value(5))
                .andExpect(jsonPath("content[0].employee.id").value(3))
                .andExpect(jsonPath("content[0].pawnItem.id").value(5));
    }

    @Test
    public void getDetailContract_id_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void etDetailContract_id_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/{id}", "5"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value(5))
                .andExpect(jsonPath("code").value("HD-NV003-5"))
                .andExpect(jsonPath("itemPrice").value(99000000))
                .andExpect(jsonPath("startDate").value("2022-05-20"))
                .andExpect(jsonPath("endDate").value("2022-06-07"))
                .andExpect(jsonPath("returnDate").value("2022-06-07"))
                .andExpect(jsonPath(".customer.id").value(5))
                .andExpect(jsonPath("employee.id").value(3))
                .andExpect(jsonPath("pawnItem.id").value(5));
    }
}
