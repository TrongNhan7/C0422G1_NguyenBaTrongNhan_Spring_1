package com.pawn_shop.model.pawn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pawn_shop.model.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PawnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "pawn_type_id", referencedColumnName = "id")
    private PawnType pawnType;

    @OneToMany(mappedBy = "pawnItem", cascade = CascadeType.ALL)
    private List<PawnImg> pawnImg;

    @OneToOne(mappedBy = "pawnItem")
    @JsonBackReference
    private Contract contract;

    private Boolean status;
}
