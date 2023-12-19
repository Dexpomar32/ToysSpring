package com.study.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Nonnull
@Entity
@Table(name = "Jucarii")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NamedStoredProcedureQuery(
        name = "Exclude",
        procedureName = "Exclude",
        resultClasses = Toy.class
)
@NamedStoredProcedureQuery(
        name = "InsertMoldova",
        procedureName = "InsertMoldova",
        resultClasses = Toy.class
)
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jucarie")
    private Integer id;
    @NaturalId
    @Column(name = "codJucarii")
    private String cod;
    @Column(name = "Nume_Jucarie")
    private String name;
    @Column(name = "Pret")
    private Double price;
    @Column(name = "Cantitate")
    private Integer quantity;
    @Column(name = "Tara_Productie")
    private String country;
    @Column(name = "Varsta_Minima")
    private Integer minimAge;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_Categorie")
    private Category category;
}