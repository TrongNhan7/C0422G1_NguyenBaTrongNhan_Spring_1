package com.pawn_shop.controller;

import com.pawn_shop.dto.CustomerDto;
import com.pawn_shop.dto.ICustomerDto;
import com.pawn_shop.model.address.Address;
import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.service.IAddressService;
import com.pawn_shop.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IAddressService iAddressService;

    @PostMapping("/saveAddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        iAddressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<Page<ICustomerDto>> getAllCustomer(@RequestParam Optional<String> name,
                                                             @PageableDefault(size = 4) Pageable pageable) {
        String name1 = name.orElse("");
        Page<ICustomerDto> customerPage = iCustomerService.findAllCustomer("%" + name1 + "%", pageable);
        if (!customerPage.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerPage, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id) {
        this.iCustomerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/goCreateCustomer")
    public ResponseEntity<Void> createNewCustomer(@RequestBody @Valid CustomerDto newCustomerDTO, BindingResult bindingResult) {
        newCustomerDTO.validate(newCustomerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(newCustomerDTO, customer);
        List<Customer> customerList = iCustomerService.findAll();
        int max = 0;
        if (customerList.isEmpty()) {
            customer.setCode("KH-001");
        } else {
            for (Customer item : customerList) {
                if (item.getCode() != null) {
                    if (Integer.parseInt(item.getCode().split("-")[1]) > max) {
                        max = Integer.parseInt(item.getCode().split("-")[1]);
                    }
                    customer.setCode("KH-" + String.format("%3s", max + 1).replaceAll(" ", "0"));
                }
            }
        }
        iCustomerService.createCustomer(customer);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PatchMapping("/goUpdateCustomer")
    public ResponseEntity<Void> updateOldCustomer(@RequestBody @Valid CustomerDto updateCustomerDTO, BindingResult bindingResult) {
        updateCustomerDTO.validate(updateCustomerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(updateCustomerDTO, customer);
        iCustomerService.updateCustomer(customer.getId(), customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
