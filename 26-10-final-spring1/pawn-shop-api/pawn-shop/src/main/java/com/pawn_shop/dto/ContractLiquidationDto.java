package com.pawn_shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Setter
@Getter
public class ContractLiquidationDto implements Validator {

    private Double liquidationPrice;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate returnDate;

    private Long idPawnItem;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractLiquidationDto contractDto = (ContractLiquidationDto) target;
        if (contractDto.returnDate == null){
            errors.rejectValue("returnDate", "r.l","* không được để trống trường này!");
        }

        if (contractDto.idPawnItem == null){
            errors.rejectValue("idPawnItem","r.l","* không được để trống trường này!");
        }

        if (contractDto.liquidationPrice == null){
            errors.rejectValue("liquidationPrice","r.l","* không được để trống trường này!");
        }
    }
}
