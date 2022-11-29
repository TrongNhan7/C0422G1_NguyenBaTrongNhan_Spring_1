package com.pawn_shop.controller;

import com.pawn_shop.model.finance.Finance;

import com.pawn_shop.service.IFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/finance")
public class FinanceController {
    @Autowired
    private IFinanceService iFinanceService;

    @GetMapping("")
    public ResponseEntity<List<Finance>> displayFinance() {
        List<Finance> finance = iFinanceService.findAllFinance();

        if (finance == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(finance, HttpStatus.OK);
        }
    }

    @GetMapping("/investment")
    public ResponseEntity<Double> displayInvestment() {
        Double totalInvestment = iFinanceService.findTotalInvestment();
        if (totalInvestment == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(totalInvestment, HttpStatus.OK);
        }
    }

    @GetMapping("/profit")
    public ResponseEntity<Double> displayExpectedProfit() {
        Double totalExpectedProfit = iFinanceService.findTotalExpectedProfit();
        if (totalExpectedProfit == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(totalExpectedProfit, HttpStatus.OK);
        }
    }
}
