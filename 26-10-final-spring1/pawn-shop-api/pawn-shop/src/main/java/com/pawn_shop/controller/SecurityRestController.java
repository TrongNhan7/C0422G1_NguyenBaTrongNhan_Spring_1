package com.pawn_shop.controller;

import com.pawn_shop.jwt.JwtUtility;
import com.pawn_shop.model.login.AppUser;
import com.pawn_shop.model.login.LoginRequest;
import com.pawn_shop.model.login.LoginResponse;
import com.pawn_shop.service.IAppUserService;
import com.pawn_shop.service.ISendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private ISendMailService sendMailService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Optional<LoginRequest> loginRequest) {

        if (!loginRequest.isPresent()) {
            return new ResponseEntity<>("Không được để trống tài khoản, mật khẩu", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.get().getUsername(), loginRequest.get().getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String employeeCode = this.appUserService.findByUsername(loginRequest.get().getUsername()).getEmployee().getCode();

        List<String> roles = userDetailService.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtility.generateJwtToken(loginRequest.get().getUsername());
            return new ResponseEntity<>(new LoginResponse(jwt, roles, loginRequest.get().getUsername(), employeeCode), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> getResetEmail(@RequestBody Optional<String> email) {

        if (!email.isPresent()) {
            return new ResponseEntity<>("Không được để trống email", HttpStatus.BAD_REQUEST);
        }

        AppUser user = this.appUserService.findByEmail(email.get());
        if (user == null) {
            return new ResponseEntity<>("Email chưa được đăng ký", HttpStatus.BAD_REQUEST);
        }
        String jwt = jwtUtility.generateJwtToken(user.getUsername());

        try {
            this.sendMailService.sendResetPasswordMail(user, email.get(), jwt);
        } catch (Exception e) {
            return new ResponseEntity<>("Không gửi được mail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
