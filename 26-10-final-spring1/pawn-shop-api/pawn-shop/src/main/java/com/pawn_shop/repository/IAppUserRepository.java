package com.pawn_shop.repository;

import com.pawn_shop.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {

    @Query(value = "SELECT \n" +
            "    id, `password`, username, employee_id\n" +
            "FROM\n" +
            "    app_user\n" +
            "WHERE\n" +
            "    username = :username",nativeQuery = true)
    AppUser findByUsername(@Param("username") String username);


    @Transactional
    @Modifying
    @Query(value = "UPDATE app_user SET `password` = :newPassword WHERE username = :username",nativeQuery = true)
    void resetPassword(@Param("username")String username, @Param("newPassword") String newPassword);

    @Query(value = "SELECT \n" +
            "    u.id, u.password, u.username, u.employee_id\n" +
            "FROM\n" +
            "    app_user AS u\n" +
            "        JOIN\n" +
            "    employee AS e ON u.employee_id = e.id\n" +
            "WHERE\n" +
            "    e.email = :email",nativeQuery = true)
    AppUser findByEmail(@Param("email") String email);
}
