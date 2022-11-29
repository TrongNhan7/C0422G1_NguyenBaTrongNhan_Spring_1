package com.pawn_shop.dto.projection;

import com.pawn_shop.model.login.AppUser;

import java.time.LocalDate;

public interface INewsDto {
    Long getId();
    String getTitle();
    String getContent();
    LocalDate getPostingDay();
    String getImgUrl();
    Boolean getStatus();
    Long getAppUser();
}
