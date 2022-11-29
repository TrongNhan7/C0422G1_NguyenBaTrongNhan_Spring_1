package com.pawn_shop.service;

import com.pawn_shop.model.finance.Finance;

import java.util.List;

public interface IFinanceService {
    List<Finance> findAllFinance();

    Double findTotalInvestment();

    Double findTotalExpectedProfit();

    void updateFinance(Double changeMoney);
}
