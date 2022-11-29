package com.pawn_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawn_shop.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestController_updateEmployee {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void updateEmployee_name_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName(null);
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_name_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_name_21() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("232323");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_name_23() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh Tran Xuan Quynh Tran Xuan Quynh Tran Xuan Quynh Tran Xuan Quynh Tran Xuan Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_address_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh");
        employeeDto.setAddress(null);
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_address_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh");
        employeeDto.setAddress("");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_dateOfBirth_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse(null));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_dateOfBirth_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse(""));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_email_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail(null);
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_email_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Quynh Tran");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_email_21() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("232323");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("32123132132");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_imgUrl_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Quynh Tran");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl(null);
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_imgUrl_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_phoneNumber_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber(null);
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_phoneNumber_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_phoneNumber_21() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("232323");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("sđgsgđ");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_idCard_19() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard(null);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_idCard_20() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_idCard_21() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("232323");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("ssssss");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_24() throws Exception {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Tran Xuan Quynh");
        employeeDto.setAddress("Đà Nẵng");
        employeeDto.setCode("NV-33");
        employeeDto.setDateOfBirth(LocalDate.parse("1999-08-08"));
        employeeDto.setEmail("quyntran@gmail.com");
        employeeDto.setGender(true);
        employeeDto.setImgUrl("f");
        employeeDto.setPhoneNumber("0905265685");
        employeeDto.setIdCard("093333333333");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/employee/update")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
