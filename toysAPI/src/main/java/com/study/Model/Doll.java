package com.study.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Nonnull
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Papusile")
public class Doll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Papusa")
    private Integer id;
    @NaturalId
    @Column(name = "codPapusa")
    private String cod;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Jucarie")
    private Toy toy;
    @Column(name = "Material")
    private String material;
    @Column(name = "Inaltime")
    private Double height;
}