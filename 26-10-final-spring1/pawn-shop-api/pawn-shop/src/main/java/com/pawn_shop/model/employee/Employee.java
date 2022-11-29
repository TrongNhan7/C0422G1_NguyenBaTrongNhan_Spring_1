package com.pawn_shop.model.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pawn_shop.model.login.AppUser;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "varchar(30)")
    private String code;

    private Boolean gender;

    private LocalDate dateOfBirth;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Column(columnDefinition = "varchar(30)")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(30)")
    private String idCard;

    @Column(columnDefinition = "varchar(150)")
    private String address;

    private Double salary;

    @Column(columnDefinition = "text")
    private String imgUrl;

    private boolean status;

    @OneToOne(mappedBy = "employee")
    @JsonBackReference
    private AppUser appUser;
}
