package com.pawn_shop.dto;

import com.pawn_shop.model.address.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
public class CustomerDto implements Validator {
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ])([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ])[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+))+$", message = "Tên không được chứa số và phải viết hoa chữ cái đầu ")
    private String name;

    private String code;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^([0-9]{10})$", message = "Không đúng định dạng SĐT")
    private String phoneNumber;

    @Email(message = "Sai định dạng email")
    @NotBlank(message = "Không được để trống")
    private String email;

    @NotNull(message = "Không được để trống")
    private LocalDate dateOfBirth;

    private Boolean gender;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^([0-9]{9})$", message = "CMND phải đủ 9 số")
    private String idCard;

    @NotBlank(message = "Không được để trống")
    private String imgUrl;

    private Boolean status;

    @NotNull(message = "Không được để trống")
    private Address address;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto newCustomerDto = (CustomerDto) target;
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(newCustomerDto.getDateOfBirth().toString());
            if (Period.between(dateOfBirth, now).getYears() < 18 || Period.between(dateOfBirth, now).getYears() > 120) {
                errors.rejectValue("dateOfBirth", "dateOfBirth.err", "tuổi từ 18-120");
            }
        } catch (Exception e) {
            errors.rejectValue("dateOfBirth", "dateOfBirth,err", "Vui lòng nhập lại");
        }

    }
    }
