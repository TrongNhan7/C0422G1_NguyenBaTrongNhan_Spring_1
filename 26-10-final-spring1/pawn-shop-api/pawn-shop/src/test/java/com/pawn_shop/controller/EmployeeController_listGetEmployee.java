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
public class EmployeeController_listGetEmployee {
    @Autowired
    private MockMvc mockMvc;
    /**
     * Create by HoanTV
     * Date: 11:59 pm  17-10-2022
     * function: check with case name equals null
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void getAllEmployee_1_nameSearchNull() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?name=null"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getAllEmployee_1_codeSearchNull() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?name=null"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Create by HoanTV
     * Date: 11:59 pm  17-10-2022
     * function: check with case search code equals is empty
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void getAllEmployee_2_nameSearchIsEmpty() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?employeeCode=  "))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getAllEmployee_2_codeSearchIsEmpty() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?employeeCode=  "))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    /**
     * Create by HoanTV
     * Date: 11:59 pm  17-10-2022
     * function: check with case code equals is doé not exist
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void getAllEmployee_3_nameDoesNotExist() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?name= 7dsjhgfuyd"))
                .andDo(print())
                .andExpect(status().is(204));
    }
    @Test
    public void getAllEmployee_3_codeDoesNotExist() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?name= geryrt325"))
                .andDo(print())
                .andExpect(status().is(204));
    }
    /**
     * Create by HoanTV
     * Date: 11:59 pm  17-10-2022
     * function: check display all list and search
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void getListStudent_4_listAndSearch() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?name=Lê Vũ Anh Thư"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].id").value(20))
                .andExpect(jsonPath("content[0].address").value("5employee - Nguyễn Chí Thanh - Đà Nẵng"))
                .andExpect(jsonPath("content[0].name").value("Lê Vũ Anh Thư"))
                .andExpect(jsonPath("content[0].code").value("NV-20"))
                .andExpect(jsonPath("content[0].phoneNumber").value("0893654999"))
                .andExpect(jsonPath("content[0].email").value("levuanhthu@gmail.com"))
                .andExpect(jsonPath("content[0].status").value("true"));
    }

    /**
     * Create by HoanTV
     * Date: 11:59 pm  17-10-2022
     * function: check page size = 0
     * HoanTV-list-employee
     *
     * @throws Exception
     */
    @Test
    public void getListStudent_5_size() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create: HoanTV
     * Date create:Date: 11:59 pm  17/10/2022
     * Return an empty list at page 3.false
     */
    @Test
    public void getListStudent_6_() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employee/list?page=3"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(19))
                .andExpect(jsonPath("content[3].id").value(2))
                .andExpect(jsonPath("content[3].address").value("222 - Hoàng Diệu - Đà Nẵng"))
                .andExpect(jsonPath("content[3].name").value("Nguyễn Văn Thuận"))
                .andExpect(jsonPath("content[3].code").value("NV-02"))
                .andExpect(jsonPath("content[3].phoneNumber").value("0873654521"))
                .andExpect(jsonPath("content[3].email").value("Keongot@gmail.com"))
                .andExpect(jsonPath("content[3].status").value("true"));

    }


}
