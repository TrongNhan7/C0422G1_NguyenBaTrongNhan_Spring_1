package com.pawn_shop.repository;

import com.pawn_shop.dto.projection.PawnItemDto;

import com.pawn_shop.model.customer.Customer;
import java.util.List;
import com.pawn_shop.model.pawn.PawnItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IPawItemRepository extends JpaRepository<PawnItem, Long> {

    @Query(value = "SELECT \n" +
            "    cus.`name` AS nameCustomer,\n" +
            "    pi.`name` AS namePawnItem,\n" +
            "    pt.`name` AS namePawnType,\n" +
            "    c.`status`,\n" +
            "    c.id AS idContract,\n" +
            "    c.item_price AS itemPrice,\n" +
            "    c.start_date AS startDate,\n" +
            "    c.end_date AS endDate\n" +
            "FROM\n" +
            "    contract c\n" +
            "        JOIN\n" +
            "    customer cus ON c.customer_id = cus.id\n" +
            "        JOIN\n" +
            "    pawn_item pi ON c.pawn_item_id = pi.id\n" +
            "        JOIN\n" +
            "    pawn_type pt ON pi.pawn_type_id = pt.id" +
            " where pt.`name` like %:itemName% and pi.`name` like %:pawnName% and c.`status`= '0'", nativeQuery = true,
            countQuery = "select count(*) from (SELECT \n" +
                    "    cus.`name` AS nameCustomer,\n" +
                    "    pi.`name` AS namePawnItem,\n" +
                    "    pt.`name` AS namePawnType,\n" +
                    "    c.`status`,\n" +
                    "    c.id AS idContract,\n" +
                    "    c.item_price AS itemPrice,\n" +
                    "    c.start_date AS startDate,\n" +
                    "    c.end_date AS endDate\n" +
                    "FROM\n" +
                    "    contract c\n" +
                    "        JOIN\n" +
                    "    customer cus ON c.customer_id = cus.id\n" +
                    "        JOIN\n" +
                    "    pawn_item pi ON c.pawn_item_id = pi.id\n" +
                    "        JOIN\n" +
                    "    pawn_type pt ON pi.pawn_type_id = pt.id" +
                    " where pt.`name` like %:itemName% and pi.`name` like %:pawnName% and c.`status`= '0')  as pawnItem")
    Page<PawnItemDto> findAllPawnItem(Pageable pageable, @Param("itemName") String itemName, @Param("pawnName") String pawnName);


    @Query(value = "SELECT" +
            "    pi.id as idPawnItem,pi.name as namePawnItem, pt.name AS namePawnType, c.item_price AS itemPrice" +
            " FROM" +
            "    pawn_item pi" +
            "        JOIN" +
            "    contract c ON c.pawn_item_id = pi.id" +
            "        JOIN" +
            "    pawn_type pt ON pt.id = pi.pawn_type_id" +
            " WHERE" +
            " pi.name LIKE %?1%" +
            " AND pi.pawn_type_id LIKE %?2%" +
            "        AND c.item_price LIKE %?3%" +
            "        AND c.status = 2",nativeQuery = true,
            countQuery = "SELECT" +
                    "    pi.id as idPawnItem,pi.name as namePawnItem, pt.name AS namePawnType, c.item_price AS itemPrice" +
                    " FROM" +
                    "    pawn_item pi" +
                    "        JOIN" +
                    "    contract c ON c.pawn_item_id = pi.id" +
                    "        JOIN" +
                    "    pawn_type pt ON pt.id = pi.pawn_type_id" +
                    " WHERE" +
                    " pi.name LIKE %:namePawnType%" +
                    " AND pi.pawn_type_id LIKE %:idPawnItem%" +
                    "        AND c.item_price LIKE %:price%" +
                    "        AND c.status = 2")
    <T> Page<T> getAllPawnItem(Pageable pageable,@Param("namePawnType") String namePawnType,@Param("idPawnItem") String idPawnItem,@Param("price") String price,Class<T> tClass);

    @Query(value = "SELECT " +
            "    i.img_url " +
            "FROM " +
            "    pawn_img AS i " +
            "        JOIN " +
            "    pawn_item AS p ON p.id = i.pawn_item_id " +
            "WHERE " +
            "    p.id = :id ",nativeQuery = true)
    List<String> findImgUrlByPawnItemId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update pawn_item set name = :name , pawn_type_id = :typeId where id = :id",nativeQuery = true)
    void updatePawnItem(@Param("name") String name, @Param("typeId") Long typeId,@Param("id") Long id);
}
