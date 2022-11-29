package com.pawn_shop.dto.projection;

import java.time.LocalDate;

public interface MailAutoProjection {
    LocalDate getStartDate();

    LocalDate getEndDate();

    String getCusTomerName();

    String getCustomerEmail();

    String getPawnItemName();

    String getCode();
}
