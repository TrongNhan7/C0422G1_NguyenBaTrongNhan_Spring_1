package com.pawn_shop.dto.quick_register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuickContractDto implements Validator {
    private Long id;
    private QuickCustomerDto quickCustomerDto;
    private QuickPawnItemDto quickPawnItemDto;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuickContractDto quickContractDto = (QuickContractDto) target;

        if (!quickContractDto.quickCustomerDto.getName().equals("")){
            if (quickContractDto.quickCustomerDto.getName().length()>40|| quickContractDto.quickCustomerDto.getName().length()<2){
                errors.rejectValue("quickCustomerDto.name",
                        "create.name",
                        "Vui lòng nhập đúng định dạng");
            }
            if (!quickContractDto.quickCustomerDto.getName().matches("^[A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]+" +
                    "( [A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]*)+$")){
                errors.rejectValue("quickCustomerDto.name",
                        "create.name",
                        "Vui lòng viết hoa chữ cái đầu");
            }
            if (quickContractDto.quickCustomerDto.getName()==null){
                errors.rejectValue("quickCustomerDto.name",
                        "create.name",
                        "Vui lòng nhập tên");
            }
        } else {
            errors.rejectValue("quickCustomerDto.name",
                    "create.name",
                    "Vui lòng nhập tên");
        }

        if (!quickContractDto.quickCustomerDto.getPhoneNumber().equals("")){
            if (!quickContractDto.quickCustomerDto.getPhoneNumber().matches("^0\\d{9}$")){
                errors.rejectValue("quickCustomerDto.phoneNumber",
                        "create.phoneNumber",
                        "Vui lòng nhập đúng định dạng");
            }
            if (quickContractDto.quickCustomerDto.getPhoneNumber() == null){
                errors.rejectValue("quickCustomerDto.phoneNumber",
                        "create.phoneNumber",
                        "Vui lòng nhập số điện thoại");
            }
        }else {
            errors.rejectValue("quickCustomerDto.phoneNumber",
                    "create.phoneNumber",
                    "Vui lòng nhập số điện thoại");
        }

        if (quickContractDto.quickCustomerDto.getQuickAddressDto().getQuickDistrictDto().getId() == null){
            errors.rejectValue("quickCustomerDto.quickAddressDto.quickDistrictDto.id",
                    "create.district",
                    "Vui lòng chọn Quận/Huyện");
        }

        if (quickContractDto.quickPawnItemDto.getQuickPawnTypeDto().getId() == null){
            errors.rejectValue("quickPawnItemDto.quickPawnTypeDto.id",
                    "create.pawnType",
                    "Vui lòng chọn loại đồ");
        }
    }
}
