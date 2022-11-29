package com.pawn_shop.repository;

import com.pawn_shop.dto.projection.IEmployeeDto;
import com.pawn_shop.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select ee.id , ee.code ,ee.name, ee.phone_number as phoneNumber, ee.address , ee.email, ee.status " +
            "from employee as ee " +
            " where ee.status " +
            "and ee.name like concat('%' , :name , '%' ) and ee.code like concat('%' , :code , '%'  ) " +
            "order by ee.id desc ", nativeQuery = true,
            countQuery = "select ee.id , ee.code, ee.name, ee.phone_number as phoneNumber, ee.address , ee.email, ee.status " +
                    "from Employee as ee " +
                    " where ee.status = 1 " +
                    "and ee.name like concat('%' , :name , '%' ) and ee.code like concat('%' , :code , '%'  ) " +
                    "order by ee.id desc ")
    Page<IEmployeeDto> getAllEmployeeSearch(@Param("name") String searchKeyWordName, @Param("code") String searchKeyWordCode, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update employee set `status` = 0 where id = :id", nativeQuery = true)
    void deleteEmployee(@Param("id") Long id);

    @Query
    (value = "select id, address,code, date_of_birth,email,gender,img_url,`name`,phone_number,salary,`status`,id_card \n" +
            "\tfrom employee \n" +
            "\twhere id = :id", nativeQuery = true)
    Optional<Employee> findById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update employee set address = :address, date_of_birth = :dateOfBirth,email = :email," +
            " gender = :gender , img_url = :imgUrl , `name` = :name, phone_number = :phoneNumber, id_card= :idCard " +
            " where id = :id", nativeQuery = true)
    void updateEmployee(@Param("address") String address, @Param("dateOfBirth") LocalDate dateOfBirth,
                        @Param("email") String email, @Param("gender") Boolean gender, @Param("imgUrl") String imgUrl,
                        @Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("idCard") String idCard, @Param("id") Long id);

    @Query
    (value = "select  e.id, e.address, `code`, e.date_of_birth as dateOfBirth, e.email, e.gender, e.img_url as imgUrl, `name`, e.phone_number as phoneNumber, e.salary, `status`, e.id_card as idCard, a.username from employee e join app_user a on e.id = a.employee_id where a.username = :user", nativeQuery = true)
    IEmployeeDto findByUser(@Param("user") String user);

}
