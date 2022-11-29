package com.pawn_shop.model.customer;

import com.pawn_shop.model.address.Address;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "varchar(30)")
    private String code;

    @Column(columnDefinition = "varchar(30)")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    private LocalDate dateOfBirth;

    private Boolean gender;

    @Column(columnDefinition = "varchar(30)")
    private String idCard;

    @Column(columnDefinition = "text")
    private String imgUrl;

    private Boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
