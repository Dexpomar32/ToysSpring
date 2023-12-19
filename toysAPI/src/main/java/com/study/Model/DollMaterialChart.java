package com.study.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQuery(
        name = "UpdateDollMaterial",
        procedureName = "UpdateDollMaterial",
        resultClasses = DollMaterialChart.class
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "dollMaterial")
public class DollMaterialChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cod")
    private String cod;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
}