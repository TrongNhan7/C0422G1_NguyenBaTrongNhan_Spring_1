package com.pawn_shop.controller;

import com.pawn_shop.model.address.District;
import com.pawn_shop.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/public/districts")
public class DistrictRestController {
    @Autowired
    private IDistrictService iDistrictService;

    @GetMapping(value = "")
    public ResponseEntity<List<District>> findAll(@RequestParam("city") Long city){
        return new ResponseEntity<>(this.iDistrictService.findAll(city), HttpStatus.OK);
    }
}
