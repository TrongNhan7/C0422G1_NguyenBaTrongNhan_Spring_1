package com.pawn_shop.controller;

import com.pawn_shop.dto.PawnItemDto;
import com.pawn_shop.model.pawn.PawnItem;
import com.pawn_shop.service.IPawItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee/pawnItem")
public class PawnItemRestController {
    @Autowired
    private IPawItemService iPawItemService;
    @PostMapping("/addPawnItem")
    public ResponseEntity<?> addPawnItem(@RequestBody @Valid PawnItemDto pawnItemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }
        PawnItem pawnItem1 = new PawnItem();
        BeanUtils.copyProperties(pawnItemDto,pawnItem1);
        iPawItemService.savePawnItem(pawnItem1);
        return new ResponseEntity<>(pawnItem1,HttpStatus.OK);
    }
    @GetMapping ("/list/pawnItem")
    public ResponseEntity<List<PawnItem>> listPawnItem() {
        List<PawnItem> pawnItemList = iPawItemService.findAllPawnItem();
        if (pawnItemList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pawnItemList,HttpStatus.OK);
    }
}
