package com.pawn_shop.dto.projection;

import com.pawn_shop.model.login.AppUser;

import java.time.LocalDate;

public interface IEmployeeDto {
    String getId();

    String getCode();

    String getName();

    String getPhoneNumber();

    String getAddress();

    String getEmail();

    String getStatus();

    String getUsername();

    Boolean getGender();

    String getIdCard();

    String getImgUrl();

    LocalDate getDateOfBirth();
}
