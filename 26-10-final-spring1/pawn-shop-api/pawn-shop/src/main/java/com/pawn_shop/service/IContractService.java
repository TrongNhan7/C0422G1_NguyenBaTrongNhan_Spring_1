package com.pawn_shop.service;

import com.pawn_shop.dto.projection.ContractDto;
import com.pawn_shop.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IContractService {

    Page<Contract> findCompleteContractByDate(String startReturnDate, String endReturnDate, Pageable pageable);

    Contract findByIdInterest(Long id);

    Page<Contract> findLiquidationContractByDate(String startReturnDate, String endReturnDate, Pageable pageable);

    Page<Contract> findExpectedContractByDate(String startReturnDate, String endReturnDate, Pageable pageable);

    <T> List<T> getAllExpectedContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass);

    <T> List<T> getAllLiquidationContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass);

    <T> List<T> getAllCompleteContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass);

    void updateStatusContract(Long idContract);

    void createLiquidation(Double price, String dateLiquidation, Long idContract);
    Long findContractByIdPawnItem(Long idPawnItem);

    Page<ContractDto> contractPage(String customerName,
                                   String pawnItemName,
                                   String type,
                                   String startDate,
                                   String endDate,
                                   String status,
                                   Pageable pageable);

    ContractDto findById(Long id);

    void deleteContract(Long id);

    Page<ContractDto> getAllContractPaginationAndSearch(Pageable pageable, String code, String customerName, String pawnItem, String startDate);

    void returnItem(Double liquidationPrice, LocalDate returnDate, long id);

    void saveContract(Contract contract);

    List<Contract> findAllContract();

    List<Contract> top10Contract();

    void updateContract(Contract contract);

    Contract findIdContract(Long id);

    Contract createQuickContract(Contract contract);

    Contract findContract();

}
