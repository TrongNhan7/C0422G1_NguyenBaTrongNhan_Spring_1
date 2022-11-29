package com.pawn_shop;

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
public class PawnShopRestController_getListCustomer {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with page 0, name null
     * Return empty list at page 1.false
     */
    @Test
    public void getAllCustomer_7_titleNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=0&name=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with title = ""
     * Return full list at page 1.true
     */
    @Test
    public void getAllCustomer_8_titleEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=0&name="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with title is not exist
     * Return an empty list at page 1.false
     */
    @Test
    public void getAllCustomer_9_titleNotExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=0&name=z"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with tittle exist
     * Return an empty list at page 1.true
     */
    @Test
    public void getAllCustomer_11_tittleExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=0&name=q"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(5))
                .andExpect(jsonPath("content[1].name").value("Đa Phúc"))
                .andExpect(jsonPath("content[1].id").value(3))
                .andExpect(jsonPath("content[1].code").value("KH-3"))
                .andExpect(jsonPath("content[1].dateOfBirth").value("1998-10-01"))
                .andExpect(jsonPath("content[1].gender").value(true))
                .andExpect(jsonPath("content[1].idCard").value("545454545"))
                .andExpect(jsonPath("content[1].imgUrl").value("2"))
                .andExpect(jsonPath("content[1].phoneNumber").value("0764052053"))
                .andExpect(jsonPath("content[1].email").value("phucnhd@gmail.com"))
                .andExpect(jsonPath("content[1].amountContract").value(1))
                .andExpect(jsonPath("content[1].addressId").value(null));;
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with page = q
     * Return full list at page 1 consists of q.true
     */
    @Test
    public void getAllCustomer_8_pageEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=&name=q"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with title is not exist
     * Return an empty list at page 1.false
     */
    @Test
    public void getAllCustomer_9_pageNotExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=11&name=q"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create: NghiaNVT
     * Date create: 17/10/2022
     * Get customer list with page exist
     * Return  list at page 1.true
     */
    @Test
    public void getAllCustomer_11_pageExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/customer/?page=0&name=q"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(5))
                .andExpect(jsonPath("content[1].name").value("Đa Phúc"))
                .andExpect(jsonPath("content[1].id").value(3))
                .andExpect(jsonPath("content[1].code").value("KH-3"))
                .andExpect(jsonPath("content[1].dateOfBirth").value("1998-10-01"))
                .andExpect(jsonPath("content[1].gender").value(true))
                .andExpect(jsonPath("content[1].idCard").value("545454545"))
                .andExpect(jsonPath("content[1].imgUrl").value("2"))
                .andExpect(jsonPath("content[1].phoneNumber").value("0764052053"))
                .andExpect(jsonPath("content[1].email").value("phucnhd@gmail.com"))
                .andExpect(jsonPath("content[1].amountContract").value(1))
                .andExpect(jsonPath("content[1].addressId").value(null));
    }
}