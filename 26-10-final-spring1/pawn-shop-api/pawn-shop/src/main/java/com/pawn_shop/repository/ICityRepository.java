package com.pawn_shop.repository;

import com.pawn_shop.model.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityRepository extends JpaRepository<City,Long> {
    @Query(value = "SELECT id, name , prefix FROM city", nativeQuery = true)
    List<City> findAll();
}
