package com.pawn_shop.dto;

import com.pawn_shop.model.contract.Contract;
import com.pawn_shop.model.pawn.PawnImg;
import com.pawn_shop.model.pawn.PawnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PawnItemDto {
    private Long id;

    @NotBlank (message = "Vui lòng nhập")
    @Size(min = 10, max = 100, message = "Nhập tối đa 100 kí tự,ít nhất 10 kí tự")
    private String name;

    private PawnType pawnType;

    private Set<PawnImg> pawnImg;

    private Contract contract;

    private Boolean status;
}
