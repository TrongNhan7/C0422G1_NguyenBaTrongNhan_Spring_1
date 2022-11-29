package com.pawn_shop.repository;

import com.pawn_shop.model.login.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppRoleRepository extends JpaRepository<AppRole,Long> {

    @Query(value = "SELECT \n" +
            "    r.id, r.role\n" +
            "FROM\n" +
            "    app_role AS r\n" +
            "        JOIN\n" +
            "    user_role AS ur ON r.id = ur.app_role_id\n" +
            "        JOIN\n" +
            "    app_user AS u ON u.id = ur.app_user_id\n" +
            "WHERE\n" +
            "    u.username = :username",nativeQuery = true)
    List<AppRole> findByUsername(@Param("username") String username);

}
