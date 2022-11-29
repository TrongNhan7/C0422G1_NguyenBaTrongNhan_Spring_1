package com.pawn_shop.repository;

import com.pawn_shop.dto.ICustomerDto;
import com.pawn_shop.model.address.Address;
import com.pawn_shop.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "Select c.code, c.name, c.id_card from customer c " +
            " where c.name like %?1% and c.id_card like %?2%")
    <T> List<T> findByNameCustomer(String name, String cmnd, Class<T> tClass);


    @Query(nativeQuery = true, value = "select count(contract.id) as amountContract ,\n" +
            " customer.id as id,\n" +
            " customer.`name` as `name`,\n" +
            " customer.`code` as `code`,\n" +
            " customer.date_of_birth as dateOfBirth,\n" +
            " customer.email as email,\n" +
            " customer.gender as gender,\n" +
            " customer.id_card as idCard,\n" +
            " customer.img_url as imgUrl,\n" +
            " customer.phone_number as phoneNumber,\n" +
            " customer.`status` as `status`\n" +
            " from customer left join contract on customer.id = contract.customer_id \n" +
            " where customer.status = 1 and customer.`name` like ?1" +
            " group by customer.id",
            countQuery = "select count(contract.id) as amountContract ,\n" +
                    " customer.id as id,\n" +
                    " customer.`name` as `name`,\n" +
                    " customer.`code` as `code`,\n" +
                    " customer.date_of_birth as dateOfBirth,\n" +
                    " customer.email as email,\n" +
                    " customer.gender as gender,\n" +
                    " customer.id_card as idCard,\n" +
                    " customer.img_url as imgUrl,\n" +
                    " customer.phone_number as phoneNumber,\n" +
                    " customer.`status` as `status`\n" +
                    " from customer left join contract on customer.id = contract.customer_id \n" +
                    " where customer.status = 1 and customer.`name` like ?1" +
                    " group by customer.id")
    Page<ICustomerDto> findAllCustomer(String name, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update customer set status= 0 where id= ?1", nativeQuery = true)
    void deleteCustomer(Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into pawn_shop.customer (`code`,`date_of_birth`, `email`, `gender`, `id_card`, `img_url`, `name`, `phone_number`, `status`,`address_id`)\n" +
            "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10) ", nativeQuery = true)
    void createCustomer(String code, LocalDate dateOfBirth,
                        String email, Boolean gender, String idCard,
                        String imgUrl, String name, String phoneNumber, Boolean status, Address address);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pawn_shop.customer\n" +
            "SET \n" +
            "`date_of_birth`= :date_of_birth,\n" +
            "`email`= :email,\n" +
            "`gender`= :gender,\n" +
            "`id_card`= :id_card,\n" +
            "`img_url`= :img_url,\n" +
            "`name`= :name,\n " +
            "`phone_number`= :phone_number,\n" +
            "`status`= :status,\n" +
            "`address_id`= :address_id\n" +
            "WHERE `id`= :id", nativeQuery = true)
    void updateCustomer(@Param("date_of_birth") LocalDate dateOfBirth, @Param("email") String email,
                        @Param("gender") Boolean gender, @Param("id_card") String idCard, @Param("img_url") String imgUrl,
                        @Param("name") String name, @Param("phone_number") String phoneNumber, @Param("status") Boolean status,
                        @Param("address_id") Long addressId, @Param("id") Long id);

    @Query(value = "select * from customer where status = 1", nativeQuery = true, countQuery = "select count(*) from (select * from customer where status =1) as tableQuery")
    List<Customer> findAllCus();

    @Query(value = "select * from customer where id = :id", nativeQuery = true)
    Optional<Customer> findCustomerById(@Param("id") Long id);

    @Query(value = "select * from customer where id_card = :idCard", nativeQuery = true)
    List<Customer> findCustomerByIdCard(@Param("idCard") String idCard);

    @Query(value = "select * from customer", nativeQuery = true)
    List<Customer> findAllCustomer();

}
