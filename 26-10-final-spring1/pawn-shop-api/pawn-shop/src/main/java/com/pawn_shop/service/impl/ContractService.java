package com.pawn_shop.service.impl;


import com.pawn_shop.dto.projection.ContractDto;
import com.pawn_shop.email.MailService;
import com.pawn_shop.model.contract.Contract;
import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.model.employee.Employee;
import com.pawn_shop.model.pawn.PawnImg;
import com.pawn_shop.repository.IContractRepository;
import com.pawn_shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService implements IContractService {

    @Autowired
    private IContractRepository iContractRepository;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private MailService mailService;

    @Autowired
    private IPawnImgService iPawnImgService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IPawItemService iPawItemService;

    @Autowired
    private IFinanceService iFinanceService;



    @Override
    public Page<Contract> findCompleteContractByDate(String startReturnDate, String endReturnDate, Pageable pageable) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.findAllCompleteContract(pageable);
        } else {
            return iContractRepository.findCompleteContractByDate(startReturnDate, endReturnDate, pageable);
        }
    }

    @Override
    public Contract findByIdInterest(Long id) {
        return this.iContractRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Contract> findLiquidationContractByDate(String startReturnDate, String endReturnDate, Pageable pageable) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.findAllLiquidatedContract(pageable);
        } else {
            return iContractRepository.findLiquidatedContractByDate(startReturnDate, endReturnDate, pageable);
        }
    }

    @Override
    public Page<Contract> findExpectedContractByDate(String startReturnDate, String endReturnDate, Pageable pageable) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.findAllExpectedContract(pageable);
        } else {
            return iContractRepository.findExpectedContractByDate(startReturnDate, endReturnDate, pageable);
        }
    }

    @Override
    public <T> List<T> getAllExpectedContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.getAllExpectedContract(tClass);
        } else {
            return iContractRepository.getAllExpectedContractByDate(startReturnDate, endReturnDate, tClass);
        }

    }

    @Override
    public <T> List<T> getAllLiquidationContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.getAllLiquidationContract(tClass);
        } else {
            return iContractRepository.getAllLiquidationContractByDate(startReturnDate, endReturnDate, tClass);
        }
    }

    @Override
    public <T> List<T> getAllCompleteContractByDate(String startReturnDate, String endReturnDate, Class<T> tClass) {
        if (startReturnDate.equals("") && endReturnDate.equals("")) {
            return iContractRepository.getAllCompleteContract(tClass);
        } else {
            return iContractRepository.getAllCompleteContractByDate(startReturnDate, endReturnDate, tClass);
        }
    }

    @Override
    public void updateStatusContract(Long idContract) {
        iContractRepository.updateStatusContract(idContract);
    }

    public void createLiquidation(Double price, String dateLiquidation, Long idContract) {
        Double currentMoney = this.iFinanceService.findAllFinance().get(0).getCurrentCapital();
        this.iFinanceService.updateFinance(currentMoney + price);
        iContractRepository.createLiquidation(price, dateLiquidation, idContract);
    }

    @Override
    public Long findContractByIdPawnItem(Long idPawnItem) {
        return iContractRepository.findContractByIdPawnItem(idPawnItem);
    }



    @Override
    public Page<ContractDto> contractPage(String customerName,
                                          String pawnItemName,
                                          String type,
                                          String startDate,
                                          String endDate,
                                          String status,
                                          Pageable pageable) {
        if (type.equals("")) {
            return iContractRepository.contractPage(
                    customerName,
                    pawnItemName,
                    1,
                    0,
                    startDate,
                    endDate,
                    status,
                    pageable);
        } else if (type.equals("1")) {
            return iContractRepository.contractPage(
                    customerName,
                    pawnItemName,
                    1,
                    1,
                    startDate,
                    endDate,
                    status,
                    pageable);
        }
        return iContractRepository.contractPage(
                customerName,
                pawnItemName,
                0,
                0,
                startDate,
                endDate,
                status,
                pageable);
    }

    @Override
    public ContractDto findById(Long id) {
        return iContractRepository.findByIdContractDto(id);
    }

    @Override
    public void deleteContract(Long id) {
        iContractRepository.deleteContract(id);
    }

    @Override
    public Page<ContractDto> getAllContractPaginationAndSearch(Pageable pageable, String code, String customerName, String pawnItem, String startDate) {
        return this.iContractRepository.getAllContractPaginationAndSearch(pageable, code, customerName, pawnItem, startDate);
    }

    @Override
    public void returnItem(Double liquidationPrice, LocalDate returnDate, long id) {
        Double currentMoney = this.iFinanceService.findAllFinance().get(0).getCurrentCapital();
        this.iFinanceService.updateFinance(currentMoney + liquidationPrice);
        this.iContractRepository.returnItem(liquidationPrice, returnDate, id);
    }

    //duyeen
    @Override
    public void saveContract(Contract contract) {
        for (PawnImg pawnImg: contract.getPawnItem().getPawnImg()) {
            pawnImg.setPawnItem(contract.getPawnItem());
            iPawnImgService.savePawnImg(pawnImg);
        }

        Contract lastContract = this.iContractRepository.findContract();
        contract.setCode(contract.getCode()+(lastContract.getId()+1));
        iContractRepository.saveContract(contract.getCode(), contract.getEndDate(), contract.getInterestRate(), contract.getItemPrice(), contract.getLiquidationPrice(), contract.getReturnDate(), contract.getStartDate(), contract.getStatus(), contract.getCustomer().getId(), contract.getEmployee().getId(), contract.getPawnItem().getId(),
                contract.getType());
        Double currentMoney = this.iFinanceService.findAllFinance().get(0).getCurrentCapital();
        this.iFinanceService.updateFinance(currentMoney - contract.getItemPrice());
        Customer customer = customerService.findCustomerById(contract.getCustomer().getId()).orElse(null);
        mailService.sendMail(contract, customer.getEmail());
    }

    @Override
    public Contract findContract() {
        return iContractRepository.findContract();
    }

    //uyên
    @Override
    public List<Contract> findAllContract() {
        return iContractRepository.findAllContract();
    }

    @Override
    public List<Contract> top10Contract() {
        return iContractRepository.top10Contract();
    }

    @Override
    public void updateContract(Contract contract) {
        for (PawnImg pawnImg: contract.getPawnItem().getPawnImg()) {
            pawnImg.setPawnItem(contract.getPawnItem());
            if(pawnImg.getStatusDelete() == 1){
                iPawnImgService.delete(pawnImg);
            }else {
                iPawnImgService.savePawnImgByUpdate(pawnImg);
            }
        }
        iPawItemService.updatePawnItem(contract.getPawnItem());
        if (contract.getStatus() == 4){
            contract.setStatus(0);
        }
        iContractRepository.updateContract(contract.getCode(),contract.getEndDate(),contract.getInterestRate(),
                contract.getItemPrice(),contract.getLiquidationPrice(),contract.getReturnDate(),contract.getStartDate(),
                contract.getType(),contract.getCustomer().getId(),contract.getEmployee().getId(),contract.getPawnItem().getId(), contract.getId(), contract.getStatus());
    }


    @Override
    public Contract findIdContract(Long id) {
        Contract contract = iContractRepository.findIdContract(id);
        if (contract.getCode() == null){
            Contract lastContract = this.iContractRepository.findContract();
            contract.setCode(String.valueOf(lastContract.getId()+1));
        }
        return contract;
    }


    @Override
    public Contract createQuickContract(Contract contract) {
        LocalDate now = LocalDate.now();
        contract.setStartDate(now);
        contract.setEndDate(now);
        contract.setStatus(4);
        contract.setType(false);
        Employee employee = this.iEmployeeService.findById(1L);
        contract.setEmployee(employee);
        return this.iContractRepository.save(contract);
    }

}

