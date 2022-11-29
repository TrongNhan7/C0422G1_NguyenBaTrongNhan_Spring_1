package com.pawn_shop.controller;

import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee")
public class CustomerRestController {
    @Autowired
    private ICustomerService iCustomerService;
    @GetMapping ("/customer/list")
    public ResponseEntity<List<Customer>> customerList () {
        List<Customer> customers = iCustomerService.findAllCus();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @GetMapping("/findCustomerById")
    public ResponseEntity<Customer> findCustomerById (@RequestParam Long id) {
        Customer customer = iCustomerService.findCustomerById(id).orElse(null);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/findCustomerByIdCard")
    public ResponseEntity<List<Customer>> findCustomerByIdCard(@RequestParam String idCard) {
        List<Customer> customerList = iCustomerService.findCustomerByIdCard(idCard);
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/getCustomerToEdit")
    public ResponseEntity<?> getCustomerToEdit(){
        return new ResponseEntity<>(iCustomerService.findAllCustomer(), HttpStatus.OK);
    }
}
