package com.pawn_shop.controller;

import com.pawn_shop.jwt.JwtUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// unit test reset password account
// author: LongTH
// time create: 20:00 - 17/10/2022

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestController_resetPassword {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private JwtUtility jwtUtility;

    //test reset password with no jwt
    @Test
    public void resetPassword_jwt_19() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/employee/reset-password"))
                .andDo(print())
                .andExpect(status().is(403));
    }

    //test reset password with newPassword = null
    @Test
    public void resetPassword_newPassword_19() throws Exception {
        String jwt = jwtUtility.generateJwtToken("user1");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/employee/reset-password")
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(jsonPath("$").value("Không được để trống"))
                .andExpect(status().is(400));
    }

    //test reset password with newPassword = null
    @Test
    public void resetPassword_newPassword_20() throws Exception {
        String jwt = jwtUtility.generateJwtToken("user1");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/employee/reset-password")
                        .content("")
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(jsonPath("$").value("Không được để trống"))
                .andExpect(status().is(400));
    }

    //test reset password with newPassword length > 30
    @Test
    public void resetPassword_newPassword_23() throws Exception {
        String jwt = jwtUtility.generateJwtToken("user1");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/employee/reset-password")
                        .content("0123456798901234567989012345679890")
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(jsonPath("$").value("Mật khẩu không được quá 30 ký tự"))
                .andExpect(status().is(400));
    }

    //test reset password with newPassword valid
    @Test
    public void resetPassword_24() throws Exception {
        String jwt = jwtUtility.generateJwtToken("user1");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/employee/reset-password")
                        .content("0123456798")
                        .header("Authorization", "Bearer " + jwt))
                .andDo(print())
                .andExpect(jsonPath("$.jwt").value(jwt))
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.role[0]").value("ROLE_EMPLOYEE"))
                .andExpect(jsonPath("$.role[1]").value("ROLE_ADMIN"))
                .andExpect(status().is(200));
    }
}
