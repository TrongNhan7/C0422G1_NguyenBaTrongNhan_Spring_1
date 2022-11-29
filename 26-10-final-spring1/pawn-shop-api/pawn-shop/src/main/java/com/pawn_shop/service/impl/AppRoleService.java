package com.pawn_shop.service.impl;

import com.pawn_shop.model.login.AppRole;
import com.pawn_shop.repository.IAppRoleRepository;
import com.pawn_shop.service.IAppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleService implements IAppRoleService {

    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> findByUsername(String username) {
        return this.appRoleRepository.findByUsername(username);
    }
}
