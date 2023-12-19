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
@Entity()
@Table(name = "Categorii")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categorie")
    private Integer id;
    @NaturalId
    @Column(name = "codCategorie")
    private String cod;
    @Column(name = "Nume_Categorie")
    private String name;
}