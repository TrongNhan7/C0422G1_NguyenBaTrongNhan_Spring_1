package com.pawn_shop.controller;

import com.pawn_shop.model.address.City;
import com.pawn_shop.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/public/cities")
public class CityRestController {
    @Autowired
    private ICityService iCityService;

    @GetMapping(value = "")
    public ResponseEntity<List<City>> findAll(){
        return new ResponseEntity<>(this.iCityService.findAll(), HttpStatus.OK);
    }
}
