package com.pawn_shop.dto.quick_register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuickAddressDto {
    private Long id;
    private QuickDistrictDto quickDistrictDto;
}
