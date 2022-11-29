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
public class InterestRestController_findExpectedContractByDate {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExpectedContractByDate_5() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/expected-contract"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_Notfound() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/expected-contract/page=10"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_6() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/interestRest/expected-contract?page=0&startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(3))
                .andExpect(jsonPath("totalElements").value(11))
                .andExpect(jsonPath("content[0].id").value(1))
                .andExpect(jsonPath("content[0].code").value("HD-NV001-1"))
                .andExpect(jsonPath("content[0].itemPrice").value(5000000))
                .andExpect(jsonPath("content[0].interestRate").value(0.2))
                .andExpect(jsonPath("content[0].startDate").value("2022-11-10"))
                .andExpect(jsonPath("content[0].endDate").value("2022-11-20"))
                .andExpect(jsonPath("content[0].customer.id").value(4))
                .andExpect(jsonPath("content[0].employee.id").value(1))
                .andExpect(jsonPath("content[0].pawnItem.id").value(1));

    }

    @Test
    public void getExpectedContractByDate_startReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?startReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_startReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?startReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_endReturnDate_1() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?endReturnDate=", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_endReturnDate_2() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?endReturnDate=", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExpectedContractByDate_starReturnDate_And_endReturnDate_TestLogic() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?page=0&startReturnDate=2022-01-13&endReturnDate=2022-01-12"))
                .andDo(print())
                .andExpect(status().is(204));
    }

    @Test
    public void getExpectedContractByDate_starReturnDate_And_endReturnDate_ok() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?page=0&startReturnDate=2022-06-13&endReturnDate=2022-10-12"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getExpectedContractByDate_starReturnDate_And_endReturnDate_4() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/interestRest/expected-contract?startReturnDate=&endReturnDate="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(3))
                .andExpect(jsonPath("totalElements").value(11))
                .andExpect(jsonPath("content[0].id").value(1))
                .andExpect(jsonPath("content[0].code").value("HD-NV001-1"))
                .andExpect(jsonPath("content[0].itemPrice").value(5000000))
                .andExpect(jsonPath("content[0].interestRate").value(0.2))
                .andExpect(jsonPath("content[0].startDate").value("2022-11-10"))
                .andExpect(jsonPath("content[0].endDate").value("2022-11-20"))
                .andExpect(jsonPath("content[0].customer.id").value(4))
                .andExpect(jsonPath("content[0].employee.id").value(1))
                .andExpect(jsonPath("content[0].pawnItem.id").value(1));
    }


}
