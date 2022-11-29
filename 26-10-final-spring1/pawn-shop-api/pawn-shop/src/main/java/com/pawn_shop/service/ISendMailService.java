package com.pawn_shop.service;


import com.pawn_shop.model.login.AppUser;

import javax.mail.internet.AddressException;

import com.pawn_shop.dto.projection.MailAutoProjection;

import javax.mail.Session;
import java.time.LocalDate;
import java.util.List;

public interface ISendMailService {

    List<MailAutoProjection> listEmail();

    void sendMailAuto(Session session);

    void sendMailReturnItem(Session session, String email, String customerName, Double liquidationPrice, String pawnItem, LocalDate returnDate);

    void sendResetPasswordMail(AppUser user, String email, String jwt) throws AddressException, Exception;

}
