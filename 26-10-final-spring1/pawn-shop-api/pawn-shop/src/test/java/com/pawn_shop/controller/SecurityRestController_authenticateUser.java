package com.pawn_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawn_shop.jwt.JwtUtility;
import com.pawn_shop.model.login.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// unit test login
// author: LongTH
// time create: 16:00 - 17/10/2022

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestController_authenticateUser {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtility jwtUtility;

    //test login with loginRequest = null
    @Test
    public void authenticateUser_13() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$").value("Không được để trống tài khoản, mật khẩu"))
                .andExpect(status().is(400));
    }

    //test login with username = null
    @Test
    public void authenticateUser_username_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("123");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is(403));
    }

    //test login with password = null
    @Test
    public void authenticateUser_password_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("123");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is(403));
    }

    //test login with wrong username
    @Test
    public void authenticateUser_username_3() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("wrongUserName");
        loginRequest.setPassword("123");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is(403));
    }

    //test login with wrong password
    @Test
    public void authenticateUser_password_3() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user1");
        loginRequest.setPassword("wrongPassword");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is(403));
    }

    //test login with right loginRequest
    @Test
    public void authenticateUser_18() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user5");
        loginRequest.setPassword("123");
        String jwt = jwtUtility.generateJwtToken("user5");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$.jwt").value(jwt))
                .andExpect(jsonPath("$.username").value("user5"))
                .andExpect(jsonPath("$.employeeId").value(5))
                .andExpect(jsonPath("$.role[0]").value("ROLE_EMPLOYEE"))
                .andExpect(status().is(200));
    }
}
