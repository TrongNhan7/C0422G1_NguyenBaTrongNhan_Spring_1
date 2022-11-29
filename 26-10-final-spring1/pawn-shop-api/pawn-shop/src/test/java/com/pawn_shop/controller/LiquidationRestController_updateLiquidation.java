//package com.pawn_shop.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pawn_shop.dto.ContractLiquidationDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class LiquidationRestController_updateLiquidation {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    /**
//     * Creator: TriDM
//     * Date created: 17-10-2022
//     * Function: findByPawnType_id_name_price_24
//     * Description: In situation with input is exist
//     */
//
//    @Test
//    public void updateLiquidation_24() throws Exception {
//
//        ContractLiquidationDto contractDto = new ContractLiquidationDto();
//        contractDto.setLiquidationPrice(100000.00);
//        contractDto.setReturnDate("2022-10-17");
//        contractDto.setCustomer(4L);
//        contractDto.setPawnItem(1L);
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .patch("/liquidation"))
//                .content(this.objectMapper.writeValueAsString(contractDto))
//                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    /**
//     * Creator: TriDM
//     * Date created: 17-10-2022
//     * Function: findByPawnType_id_name_price_19
//     * Description: In situation with input is null
//     */
//
//    @Test
//    public void updateLiquidation_19() throws Exception {
//
//        ContractLiquidationDto contractDto = new ContractLiquidationDto();
//        contractDto.setLiquidationPrice(null);
//        contractDto.setReturnDate("");
//        contractDto.setCustomer(null);
//        contractDto.setPawnItem(null);
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .patch("/liquidation"))
//                .content(this.objectMapper.writeValueAsString(contractDto))
//                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * Creator: TriDM
//     * Date created: 17-10-2022
//     * Function: updateLiquidation_21
//     * Description: In situation with input is wrong format
//     */
//
//    @Test
//    public void updateLiquidation_21() throws Exception {
//
//        ContractLiquidationDto contractDto = new ContractLiquidationDto();
//        contractDto.setLiquidationPrice(null);
//        contractDto.setReturnDate("40-09-2022");
//        contractDto.setCustomer(null);
//        contractDto.setPawnItem(null);
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .patch("/liquidation"))
//                .content(this.objectMapper.writeValueAsString(contractDto))
//                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is5xxServerError());
//    }
//}
