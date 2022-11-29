package com.pawn_shop.repository;

import com.pawn_shop.dto.projection.ContractDto;
import com.pawn_shop.dto.projection.MailAutoProjection;
import com.pawn_shop.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface IContractRepository extends JpaRepository<Contract, Long> {

    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "   start_date,\n" +
            "   end_date,\n" +
            "   return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where contract.status = 1", nativeQuery = true,
            countQuery = "select count(*) from contract where contract.status = 1")
    Page<Contract> findAllCompleteContract(Pageable pageable);


    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "   start_date,\n" +
            "   end_date,\n" +
            "   return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 1", nativeQuery = true,
            countQuery = "select count(*) from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 1")
    Page<Contract> findCompleteContractByDate(@Param("startReturnDate") String startReturnDate,
                                              @Param("endReturnDate") String endReturnDate, Pageable pageable);

    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "  start_date,\n" +
            "  end_date,\n" +
            "  return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where `status` = 3", nativeQuery = true,
            countQuery = "select count(*) from contract where `status` = 3")
    Page<Contract> findAllLiquidatedContract(Pageable pageable);


    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "  start_date,\n" +
            "  end_date,\n" +
            "  return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 3", nativeQuery = true,
            countQuery = "select count(*) from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 3")
    Page<Contract> findLiquidatedContractByDate(@Param("startReturnDate") String startReturnDate,
                                                @Param("endReturnDate") String endReturnDate, Pageable pageable);

    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "  start_date,\n" +
            "  end_date,\n" +
            "  return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where `status` = 0", nativeQuery = true,
            countQuery = "select count(*) from contract where `status` = 0")
    Page<Contract> findAllExpectedContract(Pageable pageable);


    @Query(value = "select id,\n" +
            "   code,\n" +
            "   item_price,\n" +
            "   interest_rate,\n" +
            "  start_date,\n" +
            "  end_date,\n" +
            "  return_date,\n" +
            "   status,\n" +
            "   `type`,\n" +
            "   liquidation_price,\n" +
            "   customer_id,\n" +
            "   employee_id,\n" +
            "   pawn_item_id from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 0", nativeQuery = true,
            countQuery = "select count(*) from contract where (start_date between :startReturnDate  and :endReturnDate) and `status` = 0")
    Page<Contract> findExpectedContractByDate(@Param("startReturnDate") String startReturnDate,
                                              @Param("endReturnDate") String endReturnDate, Pageable pageable);


    @Query(value = "\n" +
            "\tSELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 0 and (contract.start_date between :startReturnDate and :endReturnDate)", nativeQuery = true)
    <T> List<T> getAllExpectedContractByDate(@Param("startReturnDate") String startReturnDate,
                                             @Param("endReturnDate") String endReturnDate,
                                             Class<T> tClass);

    @Query(value = "SELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 0", nativeQuery = true)
    <T> List<T> getAllExpectedContract(Class<T> tClass);

    @Query(value = "\n" +
            "\tSELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 3 and (contract.start_date between :startReturnDate and :endReturnDate)", nativeQuery = true)
    <T> List<T> getAllLiquidationContractByDate(@Param("startReturnDate") String startReturnDate,
                                                @Param("endReturnDate") String endReturnDate,
                                                Class<T> tClass);

    @Query(value = "SELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 3", nativeQuery = true)
    <T> List<T> getAllLiquidationContract(Class<T> tClass);

    @Query(value = "\n" +
            "\tSELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 1 and (contract.start_date between :startReturnDate and :endReturnDate)", nativeQuery = true)
    <T> List<T> getAllCompleteContractByDate(@Param("startReturnDate") String startReturnDate,
                                             @Param("endReturnDate") String endReturnDate,
                                             Class<T> tClass);

    @Query(value = "SELECT \n" +
            "    contract.id,\n" +
            "    contract.code,\n" +
            "    contract.item_price AS itemPrice,\n" +
            "    contract.interest_rate AS interestRate,\n" +
            "    contract.start_date AS startDate,\n" +
            "    contract.end_date AS endDate,\n" +
            "    contract.return_date AS returnDate,\n" +
            "    contract.type,\n " +
            "    contract.status,\n" +
            "    contract.liquidation_price AS liquidationPrice,\n" +
            "    customer.name AS customer,\n" +
            "    employee.name AS employee,\n" +
            "    pawn_item.name AS pawnItem\n" +
            "FROM\n" +
            "    contract\n" +
            "        JOIN\n" +
            "    customer ON contract.customer_id = customer.id\n" +
            "        JOIN\n" +
            "    employee ON contract.employee_id = employee.id\n" +
            "        JOIN\n" +
            "    pawn_item ON contract.pawn_item_id = pawn_item.id\n" +
            "WHERE\n" +
            "    contract.status = 1", nativeQuery = true)
    <T> List<T> getAllCompleteContract(Class<T> tClass);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `pawn_shop`.`contract` SET `status` = '2' WHERE (`id` = :idContract)", nativeQuery = true)
    void updateStatusContract(@Param("idContract") Long idContract);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `pawn_shop`.`contract` " +
            "SET " +
            "    `liquidation_price` = ?1," +
            "    `return_date` = ?2," +
            "    `status` = 3 ," +
            "    `type` = 1 " +
            "WHERE" +
            "    (`id` = ?3);")
    void createLiquidation(Double price, String dateLiquidation, Long idContract);

    @Query(value = "select c.id from contract c" +
            " where c.pawn_item_id = ?1", nativeQuery = true)
    Long findContractByIdPawnItem(Long idPawnItem);


    @Query(nativeQuery = true, value = "select ct.id as id, ct.code as code, c.name as customer, p.name as pawnItem, " +
            "pt.name as pawnTypeName, ct.start_date as startDate, ct.end_date as endDate, ct.type as type, ct.status as status " +
            " from contract ct right join customer c " +
            " on ct.customer_id = c.id right join pawn_item p on ct.pawn_item_id = p.id right join pawn_type pt on pt.id = p.pawn_type_id where c.name like ?1 and " +
            " p.name like ?2 and ( ct.type = ?3 or ct.type = ?4 ) and ct.start_date >= ?5 and ct.end_date <= ?6 and ct.status like ?7 and ct.status <> 5 order by ct.start_date desc ",

            countQuery = "select ct.id as id, ct.code as code, c.name as customer, p.name as pawnItem, " +
                    "pt.name as pawnTypeName, ct.start_date as startDate, ct.end_date as endDate, ct.type as type, ct.status as status " +
                    "from contract ct right join customer c " +
                    "on ct.customer_id = c.id right join pawn_item p on ct.pawn_item_id = p.id right join pawn_type pt on pt.id = p.pawn_type_id where c.name like ?1 and " +
                    "p.name like ?2 and ( ct.type = ?3 or ct.type = ?4 ) and ct.start_date >= ?5 and ct.end_date <= ?6 and ct.status like ?7 and ct.status <> 5 order by ct.start_date desc ")
    Page<ContractDto> contractPage(String customerName,
                                   String pawnItemName,
                                   int type,
                                   int type1,
                                   String startDate,
                                   String endDate,
                                   String status,
                                   Pageable pageable);

    @Query(nativeQuery = true, value = "select ct.id as id, ct.code as code, c.name as customer, " +
            "ct.start_date as startDate, ct.end_date as endDate, " +
            "p.name as pawnItem, e.name as employee, ct.type as type, " +
            "c.email as customerEmail, ct.item_price as itemPrice, " +
            "ct.status as status, ct.return_date as returnDate, c.phone_number as phoneNumber, " +
            "ct.interest_rate as interestRate, ct.liquidation_price as liquidationPrice " +
            "from contract ct join customer c on ct.customer_id = c.id " +
            "join pawn_item p on ct.pawn_item_id = p.id " +
            "join pawn_type pt on pt.id = p.pawn_type_id " +
            "join employee e on ct.employee_id = e.id " +
            "where ct.id = ?1 and ct.status <> 5 ")
    ContractDto findByIdContractDto(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update contract ct set ct.status = 5 where ct.id = ?1")
    void deleteContract(Long id);

    @Query(nativeQuery = true, value = "select ct.code as code, ct.start_date as startDate, ct.end_date as endDate, c.name as customerName, c.email as customerEmail, pt.name as pawnItemName " +
            "from contract ct right join customer c on ct.customer_id = c.id right join pawn_item pt on " +
            "ct.pawn_item_id = pt.id where ( ct.end_date = ?1 or ct.end_date = ?2 or ct.end_date = ?3 or ct.end_date = ?4 ) and ct.status <> 5 ")
    List<MailAutoProjection> listEmailAutoSend(LocalDate beforeOneDay,
                                               LocalDate beforeThreeDay,
                                               LocalDate beforeSevenDay,
                                               LocalDate afterOneDay);

    @Query(value = "select ct.id, ct.code, c.name as customer, pi.name as pawnItem, " +
            " ct.item_price as itemPrice, ct.start_date as startDate, " +
            " ct.end_date as endDate, ct.interest_rate as interestRate, " +
            " ct.return_date as returnDate, ct.liquidation_price as liquidationPrice, c.email as customerEmail " +
            " from contract ct " +
            " join customer c on ct.customer_id = c.id " +
            " join pawn_item pi on ct.pawn_item_id = pi.id " +
            " where (ct.code like %:code% and c.name like %:customerName% " +
            " and pi.name like %:pawnItem% and ct.start_date like %:startDate%) " +
            " and ct.status = 0", nativeQuery = true,
            countQuery = "select count(*) from (" +
                    " select ct.id, ct.code, c.name as customer, pi.name as pawnItem, " +
                    " ct.item_price as itemPrice, ct.start_date as startDate, " +
                    " ct.end_date as endDate, ct.interest_rate as interestRate, " +
                    " ct.return_date as returnDate, ct.liquidation_price as liquidationPrice, c.email as customerEmail " +
                    " from contract ct " +
                    " join customer c on ct.customer_id = c.id " +
                    " join pawn_item pi on ct.pawn_item_id = pi.id " +
                    " where (ct.code like %:code% and c.name like %:customerName% " +
                    " and pi.name like %:pawnItem% and ct.start_date like %:startDate%) " +
                    " and ct.status = 0) contracts")
    Page<ContractDto> getAllContractPaginationAndSearch(Pageable pageable, @Param("code") String code, @Param("customerName") String customerName,
                                                        @Param("pawnItem") String pawnItem, @Param("startDate") String startDate);

    @Modifying
    @Query(value = "update contract set status = 1, liquidation_price = :liquidationPrice, return_date = :returnDate where id = :id", nativeQuery = true)
    void returnItem(@Param("liquidationPrice") Double liquidationPrice, @Param("returnDate") LocalDate returnDate, @Param("id") long id);

    // duyên
    @Modifying
    @Transactional
    @Query(value = "insert into contract (code,end_date,interest_rate,item_price,liquidation_price,return_date,start_date,status,customer_id,employee_id," +
            "pawn_item_id,`type`) value (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)", nativeQuery = true)
    void saveContract(String code, LocalDate endDate, Double interestRate, Double itemPrice, Double liquidationPrice, LocalDate returnDate, LocalDate startDate, int status, Long customerId, Long employeeId, Long pawnItemId, boolean type);

    @Query(value = "select * from contract order by contract.id desc limit 1", nativeQuery = true)
    Contract findContract();

    //uyên
    @Modifying
    @Query(value = "update contract set code = :code , end_date = :endDate, interest_rate = :interestRate, item_price = :itemPrice," +
            "liquidation_price = :liquidationPrice, return_date = :returnDate,start_date = :startDate, type = :type," +
            "customer_id = :customerId, employee_id = :employeeId, pawn_item_id = :pawnItemId, status = :status where id = :id ", nativeQuery = true)
    void updateContract(@Param("code") String code, @Param("endDate") LocalDate endDate, @Param("interestRate") Double interestDate,
                        @Param("itemPrice") Double itemPrice, @Param("liquidationPrice") Double liquidationPrice, @Param("returnDate") LocalDate returnDate,
                        @Param("startDate") LocalDate startDate, @Param("type") Boolean type,
                        @Param("customerId") Long customerId, @Param("employeeId") Long employeeId,
                        @Param("pawnItemId") Long pawnItemId, @Param("id") Long id, @Param("status") Integer status);

    @Query(value = "select * from contract where start_date = current_date order by id desc limit 10 ", nativeQuery = true)
    List<Contract> top10Contract();

    @Query(value = "select  * from contract where status = 0", nativeQuery = true)
    List<Contract> findAllContract();


    @Query(value = "select * from contract where id = :id", nativeQuery = true)
    Contract findIdContract(@Param("id") Long id);

}

