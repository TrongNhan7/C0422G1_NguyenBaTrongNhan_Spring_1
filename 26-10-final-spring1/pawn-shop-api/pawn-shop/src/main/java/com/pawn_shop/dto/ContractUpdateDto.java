package com.pawn_shop.dto;

import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.model.employee.Employee;
import com.pawn_shop.model.pawn.PawnItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
public class ContractUpdateDto implements Validator {

    private Long id;

    private String code;

    private String itemPrice;

    private String interestRate;

    private String startDate;

    private String endDate;

    private LocalDate returnDate;

    private String liquidationPrice;

    private Boolean type;

    private int status;

    private PawnItem pawnItem;

    private Customer customer;

    private Employee employee;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractUpdateDto contractUpdateDto = (ContractUpdateDto) target;
        Double itemPrice;
        if (contractUpdateDto.getItemPrice() == "" ) {
            errors.rejectValue("itemPrice","add.itemPrice","Vui lòng nhập");
        }try {
            itemPrice = Double.parseDouble(contractUpdateDto.getItemPrice());
            if (itemPrice<=0) {
                errors.rejectValue("itemPrice","add.itemPrice","Vui lòng nhập giá đồ cầm lơn hơn 0");
            }
        } catch (Exception e) {
            errors.rejectValue("itemPrice","add.itemPrice","Vui lòng nhập số");
        }

        Double interestRate;
        if (contractUpdateDto.getInterestRate() == "" ) {
            errors.rejectValue("interestRate","add.interestRate","Vui lòng nhập");
        }try {
            interestRate = Double.parseDouble(contractUpdateDto.getInterestRate());
            if (interestRate <0.2 || interestRate > 0.4) {
                errors.rejectValue("interestRate","add.interestRate","Vui lòng nhập lãi suất trong khoảng 0.2 đến 0.4");
            }
        } catch (Exception e) {
            errors.rejectValue("interestRate","add.interestRate","Vui lòng nhập số");
        }

        LocalDate startDate;
        if (contractUpdateDto.startDate.isEmpty()) {
            errors.rejectValue("startDate","add.startDate","Vui lòng nhập");
        } else {
            try {
                startDate = LocalDate.parse(contractUpdateDto.startDate);
            } catch (Exception e) {
                errors.rejectValue("startDate","add.startDate","Vui lòng nhập đúng định dạng dd/MM/yyyy");
            }
        }

        LocalDate endDate;
        if (contractUpdateDto.endDate=="") {
            errors.rejectValue("endDate","add.endDate","Vui lòng nhập");
        } else {
            try {
                endDate = LocalDate.parse(contractUpdateDto.endDate);
                if (endDate.isBefore(LocalDate.parse(contractUpdateDto.startDate)) || endDate.equals(LocalDate.parse(contractUpdateDto.startDate))) {
                    errors.rejectValue("endDate","add.endDate","Vui lòng nhập ngày kết thúc hợp đồng lớn hơn ngày làm hợp đồng");
                }
            } catch (Exception e) {
                errors.rejectValue("endDate","add.endDate","Vui lòng nhập đúng định dạng dd/MM/yyyy");
            }
        }
    }

}