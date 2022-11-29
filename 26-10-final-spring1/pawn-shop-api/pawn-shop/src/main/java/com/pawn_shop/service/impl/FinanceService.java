package com.pawn_shop.service.impl;

import com.pawn_shop.model.finance.Finance;
import com.pawn_shop.repository.IFinanceRepository;
import com.pawn_shop.service.IFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FinanceService implements IFinanceService {

    @Autowired
    private IFinanceRepository iFinanceRepository;

    @Override
    public List<Finance> findAllFinance() {   
        return iFinanceRepository.findAllFinance();
    }

    @Override
    public Double findTotalInvestment() {
        return iFinanceRepository.findTotalInvestment();
    }

    @Override
    public Double findTotalExpectedProfit() {
        return iFinanceRepository.findTotalExpectedProfit();
    }

    @Override
    public void updateFinance(Double changeMoney) {
        this.iFinanceRepository.updateFinance(changeMoney);
    }

}
