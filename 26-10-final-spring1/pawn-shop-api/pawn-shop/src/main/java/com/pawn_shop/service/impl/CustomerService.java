package com.pawn_shop.service.impl;

import com.pawn_shop.dto.ICustomerDto;
import com.pawn_shop.model.address.Address;
import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.repository.ICustomerRepository;
import com.pawn_shop.service.IAddressService;
import com.pawn_shop.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private IAddressService iAddressService;

    @Override
    public Page<ICustomerDto> findAllCustomer(String name, Pageable pageable) {
        return iCustomerRepository.findAllCustomer(name, pageable);
    }

    @Override
    public void deleteCustomer(Integer id) {
        iCustomerRepository.deleteCustomer(id);
    }

    @Override
    public void createCustomer(Customer newCustomer) {
        Address address = iAddressService.save(newCustomer.getAddress());
        newCustomer.setAddress(address);

        iCustomerRepository.createCustomer(newCustomer.getCode(), newCustomer.getDateOfBirth(), newCustomer.getEmail(),
                newCustomer.getGender(), newCustomer.getIdCard(), newCustomer.getImgUrl(), newCustomer.getName(),
                newCustomer.getPhoneNumber(), newCustomer.getStatus(), newCustomer.getAddress());
    }

    @Override
    public void updateCustomer(Long id, Customer oldCustomer) {
        Address address = iAddressService.save(oldCustomer.getAddress());
        oldCustomer.setAddress(address);
        iCustomerRepository.updateCustomer(oldCustomer.getDateOfBirth(), oldCustomer.getEmail(),
                oldCustomer.getGender(), oldCustomer.getIdCard(), oldCustomer.getImgUrl(), oldCustomer.getName(),
                oldCustomer.getPhoneNumber(), oldCustomer.getStatus(), oldCustomer.getAddress().getId(), oldCustomer.getId());
    }

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public List<Customer> findAllCus() {
        return iCustomerRepository.findAllCus();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return iCustomerRepository.findCustomerById(id);
    }

    @Override
    public List<Customer> findCustomerByIdCard(String idCard) {
        return iCustomerRepository.findCustomerByIdCard(idCard);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return iCustomerRepository.findAllCustomer();
    }

    @Override
    public Customer createQuickCustomer(Customer customer) {
        customer.setStatus(true);
        customer.setGender(true);
        return this.iCustomerRepository.save(customer);

    }
}
