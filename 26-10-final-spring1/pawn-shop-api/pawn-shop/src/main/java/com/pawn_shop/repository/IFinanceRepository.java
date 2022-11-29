package com.pawn_shop.repository;

import com.pawn_shop.model.finance.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IFinanceRepository extends JpaRepository<Finance, Long> {

    @Query(value = "select * from finance;", nativeQuery = true)
    List<Finance> findAllFinance();

    @Query(value = "select sum(item_price) as total_investment from contract where status =0", nativeQuery = true)
    Double findTotalInvestment();

    @Query(value = "select sum(DATEDIFF(end_date,start_date)*(interest_rate/100)*item_price) as total_expected_profit from contract where status = 0 ", nativeQuery = true)
    Double findTotalExpectedProfit();

    @Modifying
    @Query(value = "UPDATE `pawn_shop`.`finance` SET `current_capital` = :currentCapital WHERE (`id` = '1') ", nativeQuery = true)
    void updateFinance(@Param("currentCapital") Double currentCapital);
}
