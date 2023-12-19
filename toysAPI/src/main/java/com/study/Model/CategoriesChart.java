package com.study.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQuery(
        name = "UpdateCategoriesChart",
        procedureName = "UpdateCategoriesChart",
        resultClasses = CategoriesChart.class
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class CategoriesChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CategoryChart")
    private Integer id;
    @Column(name = "codCategoryChart")
    private String cod;
    @Column(name = "Nume_Categorie")
    private String name;
    @Column(name = "Total_Toys_Count")
    private Integer quantity;
}