package com.pawn_shop.service.impl;

import com.pawn_shop.model.login.AppUser;
import com.pawn_shop.repository.IAppUserRepository;
import com.pawn_shop.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public AppUser findByUsername(String username) {
        return this.appUserRepository.findByUsername(username);
    }

    @Override
    public void resetPassword(String username, String newPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        this.appUserRepository.resetPassword(username, encodePassword);
    }

    @Override
    public AppUser findByEmail(String email) {
        return this.appUserRepository.findByEmail(email);
    }

}
