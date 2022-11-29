package com.pawn_shop.model.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pawn_shop.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(150)")
    private String street;

    @ManyToOne
    @JoinColumn(name = "district_id",referencedColumnName = "id")
    private District district;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Customer customer;
}
