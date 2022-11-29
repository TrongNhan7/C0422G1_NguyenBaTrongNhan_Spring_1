package com.pawn_shop.service;

import com.pawn_shop.model.address.District;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDistrictService {
    List<District> findAll(Long city);
}
