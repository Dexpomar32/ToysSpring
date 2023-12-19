package com.study.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;

@Nonnull
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Vanzari")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Vanzare")
    private Integer id;
    @NaturalId
    @Column(name = "codVanzare")
    private String cod;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Jucarie")
    private Toy toy;
    @Column(name = "Data_Vanzare")
    private Date saleDate;
    @Column(name = "Cantitate_Vanduta")
    private Integer quantity;
}