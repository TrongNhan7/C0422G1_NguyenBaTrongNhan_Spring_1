package com.pawn_shop.service;

import com.pawn_shop.model.address.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityService {
    List<City> findAll();
}
