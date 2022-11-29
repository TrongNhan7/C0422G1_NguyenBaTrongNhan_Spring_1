package com.pawn_shop.model.contract;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pawn_shop.model.customer.Customer;
import com.pawn_shop.model.employee.Employee;
import com.pawn_shop.model.pawn.PawnItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(30)")
    private String code;

    private Double itemPrice;

    private Double interestRate;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private Double liquidationPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pawn_item_id",referencedColumnName = "id")
    private PawnItem pawnItem;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private Employee employee;

    private Boolean type;

    private int status;
}
