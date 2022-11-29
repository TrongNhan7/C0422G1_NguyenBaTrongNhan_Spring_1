package com.pawn_shop.repository;

import com.pawn_shop.model.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDistricRepository extends JpaRepository<District, Long> {
    @Query(value = "SELECT id, name, prefix, city_id FROM district where city_id = ?1", nativeQuery = true)
    List<District> findAll(Long city);
}
