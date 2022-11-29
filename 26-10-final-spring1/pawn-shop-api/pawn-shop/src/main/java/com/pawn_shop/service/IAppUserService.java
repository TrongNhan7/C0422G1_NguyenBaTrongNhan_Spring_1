package com.pawn_shop.service;

import com.pawn_shop.model.login.AppUser;

public interface IAppUserService {

    AppUser findByUsername(String username);

    void resetPassword(String username, String newPassword);

    AppUser findByEmail(String email);
}
