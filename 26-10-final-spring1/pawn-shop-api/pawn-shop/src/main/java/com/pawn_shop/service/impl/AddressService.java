package com.pawn_shop.service.impl;

import com.pawn_shop.model.address.Address;
import com.pawn_shop.repository.IAddressRepository;
import com.pawn_shop.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    AddressService implements IAddressService {
    @Autowired
    private IAddressRepository iAddressRepository;

    @Override
    public Address save(Address address) {
        return iAddressRepository.save(address);
    }

    @Override
    public Address createQuickAddress(Address address) {
        return this.iAddressRepository.save(address);
    }
}
