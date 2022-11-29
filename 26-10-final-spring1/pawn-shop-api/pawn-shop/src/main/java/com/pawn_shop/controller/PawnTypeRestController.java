package com.pawn_shop.controller;

import com.pawn_shop.dto.quick_register.QuickContractDto;
import com.pawn_shop.model.address.Address;
import com.pawn_shop.model.address.District;
import com.pawn_shop.model.contract.Contract;
import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.model.pawn.PawnItem;
import com.pawn_shop.model.pawn.PawnType;
import com.pawn_shop.service.*;
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
@RequestMapping("/api/public")
public class PawnTypeRestController {
    @Autowired
    private IPawnTypeService iPawnTypeService;

    @Autowired
    private IPawItemService iPawnItemService;

    @Autowired
    private IAddressService iAddressService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IContractService contractService;

    @GetMapping("/contract/pawnType")
    public ResponseEntity<List<PawnType>> pawnTypeList() {
        List<PawnType> pawnTypeList = iPawnTypeService.findAllPawnType();
        if (pawnTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pawnTypeList, HttpStatus.OK);
    }


    //    Truong bat dau
    @PostMapping(value = "/createQuickContract")
    public ResponseEntity<?> createQuickContract(@RequestBody @Valid QuickContractDto quickContractDto,
                                                 BindingResult bindingResult) {
        new QuickContractDto().validate(quickContractDto, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }
        PawnItem tempPawnItem = new PawnItem();
        Address tempAddress = new Address();
        Customer tempCustomer = new Customer();
        Contract tempContract = new Contract();

        PawnType tempPawnType = new PawnType();
        tempPawnType.setId(quickContractDto.getQuickPawnItemDto().getQuickPawnTypeDto().getId());
        tempPawnItem.setPawnType(tempPawnType);
        PawnItem pawnItem = this.iPawnItemService.createQuickPawnItem(tempPawnItem);

        District tempDistrict = new District();
        tempDistrict.setId(quickContractDto.getQuickCustomerDto().getQuickAddressDto().getQuickDistrictDto().getId());
        tempAddress.setDistrict(tempDistrict);
        Address address = this.iAddressService.createQuickAddress(tempAddress);

        tempCustomer.setAddress(address);
        tempCustomer.setName(quickContractDto.getQuickCustomerDto().getName());
        tempCustomer.setPhoneNumber(quickContractDto.getQuickCustomerDto().getPhoneNumber());
        Customer customer = this.iCustomerService.createQuickCustomer(tempCustomer);

        tempContract.setCustomer(customer);
        tempContract.setPawnItem(pawnItem);
        Contract contract = this.contractService.createQuickContract(tempContract);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }
//    Truong ket thuc
}
