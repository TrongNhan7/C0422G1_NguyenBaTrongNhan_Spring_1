package com.pawn_shop.model.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(30)")
    private String prefix;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<District> districts;

}
