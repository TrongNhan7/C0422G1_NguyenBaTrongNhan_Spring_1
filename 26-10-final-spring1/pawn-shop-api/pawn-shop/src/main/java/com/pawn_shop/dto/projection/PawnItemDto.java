package com.pawn_shop.dto.projection;

import java.time.LocalDate;

public interface PawnItemDto {
    String getNameCustomer();

    String getNamePawnItem();

    String getNamePawnType();

    Integer getStatus();

    Double getItemPrice();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Long getIdContract();
}
