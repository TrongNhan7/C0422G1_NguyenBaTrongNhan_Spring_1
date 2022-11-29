package com.pawn_shop.dto.projection;

import java.time.LocalDate;

public interface DetailContractPawnItemDto {
    String getNameCustomer();

    String getNamePawnItem();

    String getNamePawnType();

    Double getItemPrice();

    LocalDate getStartDate();

    LocalDate getEndDate();
}
