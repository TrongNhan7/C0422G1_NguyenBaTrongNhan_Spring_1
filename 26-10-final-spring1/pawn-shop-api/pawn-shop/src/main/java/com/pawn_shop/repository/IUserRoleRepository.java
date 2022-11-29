package com.pawn_shop.repository;

import com.pawn_shop.model.login.UserRole;
import com.pawn_shop.model.login.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
}
