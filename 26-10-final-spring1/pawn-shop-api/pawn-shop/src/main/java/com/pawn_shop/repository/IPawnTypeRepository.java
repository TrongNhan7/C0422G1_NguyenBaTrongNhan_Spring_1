package com.pawn_shop.repository;

import com.pawn_shop.model.pawn.PawnType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPawnTypeRepository extends JpaRepository<PawnType, Long> {
    @Query(value = "select * from pawn_type", nativeQuery = true)
    List<PawnType> findAllPawnType();

    @Query(nativeQuery = true,value = "select pt.id, pt.name from pawn_type pt ")
    <T> List<T> findAllPawnTypeLiquidation(Class<T> tClass);

    @Query(value = "update pawn_type set name = :name where id = :id", nativeQuery = true)
    void updatePawnType(@Param("name") String name, @Param("id") Long id);
}

