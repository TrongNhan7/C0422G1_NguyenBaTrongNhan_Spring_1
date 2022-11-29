package com.pawn_shop.controller;

import com.pawn_shop.jwt.JwtFilter;
import com.pawn_shop.jwt.JwtUtility;
import com.pawn_shop.model.login.AppRole;
import com.pawn_shop.model.login.LoginResponse;
import com.pawn_shop.service.IAppRoleService;
import com.pawn_shop.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeRestController {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IAppRoleService appRoleService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private JwtFilter jwtFilter;

    @PatchMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Optional<String> newPassword, HttpServletRequest request) {
        if (!newPassword.isPresent() || newPassword.get().equals("")) {
            return new ResponseEntity<>("Không được để trống", HttpStatus.BAD_REQUEST);
        } else {
            if (String.valueOf(newPassword).length() > 30) {
                return new ResponseEntity<>("Mật khẩu không được quá 30 ký tự", HttpStatus.BAD_REQUEST);
            }
        }
        String jwt = jwtFilter.parseJwt(request);
        String username = jwtUtility.getUserNameFromJwtToken(jwt);

        this.appUserService.resetPassword(username, newPassword.get());

        List<AppRole> roles = this.appRoleService.findByUsername(username);
        List<String> roleList = new ArrayList<>();
        for (AppRole role : roles) {
            roleList.add(role.getRole());
        }

        String employeeCode = this.appUserService.findByUsername(username).getEmployee().getCode();

        return new ResponseEntity<>(new LoginResponse(jwt, roleList, username, employeeCode), HttpStatus.OK);
    }
}
