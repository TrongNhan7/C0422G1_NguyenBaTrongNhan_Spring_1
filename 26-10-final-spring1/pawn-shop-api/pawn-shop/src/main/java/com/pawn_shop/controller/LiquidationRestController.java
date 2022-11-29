package com.pawn_shop.controller;

import com.pawn_shop.dto.ContractLiquidationDto;
import com.pawn_shop.dto.IPawnItemLiquidationDto;
import com.pawn_shop.dto.IPawnTypeLiquidationDto;
import com.pawn_shop.service.IContractService;
import com.pawn_shop.service.IPawItemService;
import com.pawn_shop.service.IPawnTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee/liquidation")
public class LiquidationRestController {

    @Autowired
    private IPawItemService iPawItemService;

    @Autowired
    private IPawnTypeService iPawnTypeService;

    @Autowired
    private IContractService iContractService;

    @GetMapping("/pawnItem/list")
    public ResponseEntity<Page<IPawnItemLiquidationDto>> findByNameAndPricePawnItem(@RequestParam("namePawnItem") Optional<String> namePawnType,
                                                                                    @RequestParam("idPawnType") Optional<String> idPawnItem,
                                                                                    @RequestParam("price") Optional<String> price,
                                                                                    @PageableDefault(size = 5) Pageable pageable) {
        String namePawnTypes = namePawnType.orElse("");
        String idPawnItems = idPawnItem.orElse("");
        String prices = price.orElse("");
        Page<IPawnItemLiquidationDto> list = iPawItemService.getAllPawnItem(pageable, namePawnTypes, idPawnItems, prices, IPawnItemLiquidationDto.class);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pawnType/list")
    public ResponseEntity<List<IPawnTypeLiquidationDto>> findByPawnType() {
        List<IPawnTypeLiquidationDto> list = iPawnTypeService.findAllPawnType(IPawnTypeLiquidationDto.class);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findContractByIdItem")
    public ResponseEntity<Long> findContractByIdPawnItem(@RequestParam("id") Optional<Long> idItem) {
        if (idItem == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iContractService.findContractByIdPawnItem(idItem.orElse(null)), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Map<String, String>> updateLiquidation(@Valid @RequestBody ContractLiquidationDto contractDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errMap = new HashMap<>();
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
            } else {
                this.iContractService.createLiquidation(contractDto.getLiquidationPrice(), String.valueOf(LocalDate.now())
                        , iContractService.findContractByIdPawnItem(contractDto.getIdPawnItem()));
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pawn-img/{pawnItemId}")
    public ResponseEntity<List<String>> getImgUrl(@PathVariable Long pawnItemId) {
        return new ResponseEntity<>(this.iPawItemService.findImgUrlByPawnItemId(pawnItemId), HttpStatus.OK);
    }
}
