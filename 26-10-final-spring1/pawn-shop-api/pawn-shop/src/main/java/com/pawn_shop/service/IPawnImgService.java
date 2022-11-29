package com.pawn_shop.service;

import com.pawn_shop.model.pawn.PawnImg;

import java.util.List;

public interface IPawnImgService {


    void savePawnImg (PawnImg pawnImg);

    void delete(PawnImg pawnImg);

    void savePawnImgByUpdate(PawnImg pawnImg);

    List<PawnImg> findAll();
}
