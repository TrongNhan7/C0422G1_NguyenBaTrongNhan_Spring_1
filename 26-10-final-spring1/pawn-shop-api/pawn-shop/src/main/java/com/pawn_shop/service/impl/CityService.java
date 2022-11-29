package com.pawn_shop.service.impl;

import com.pawn_shop.model.address.City;
import com.pawn_shop.repository.ICityRepository;
import com.pawn_shop.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository iCityRepository;

    @Override
    public List<City> findAll() {
        return this.iCityRepository.findAll();
    }
}
