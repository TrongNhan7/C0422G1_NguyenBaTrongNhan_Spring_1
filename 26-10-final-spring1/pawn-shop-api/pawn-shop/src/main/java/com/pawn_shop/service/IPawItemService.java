package com.pawn_shop.service;

import com.pawn_shop.dto.projection.PawnItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pawn_shop.model.pawn.PawnItem;

import java.util.List;

public interface IPawItemService {
    Page<PawnItemDto> findAllPawnItem(Pageable pageable, String itemName, String pawnName);

    <T> Page<T> getAllPawnItem(Pageable pageable,String namePawnType,String idPawnItem,String price, Class<T> tClass);

    PawnItem savePawnItem(PawnItem pawnItem);

    List<PawnItem> findAllPawnItem();

    PawnItem createQuickPawnItem(PawnItem pawnItem);

    List<String> findImgUrlByPawnItemId(Long id);

    void updatePawnItem(PawnItem pawnItem);
}

