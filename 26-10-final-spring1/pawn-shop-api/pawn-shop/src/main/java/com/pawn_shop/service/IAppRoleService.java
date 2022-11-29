package com.pawn_shop.service;

import com.pawn_shop.model.login.AppRole;

import java.util.List;

public interface IAppRoleService {

    List<AppRole> findByUsername(String username);
}
