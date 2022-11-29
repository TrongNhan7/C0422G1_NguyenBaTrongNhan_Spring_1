package com.pawn_shop.service;

import com.pawn_shop.model.address.Address;

public interface IAddressService {

    Address save(Address address);

    Address createQuickAddress(Address address);

}
