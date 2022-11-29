package com.pawn_shop.repository;

import com.pawn_shop.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
