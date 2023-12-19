package com.study.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQuery(
        name = "UpdateCountriesChart",
        procedureName = "UpdateCountriesChart",
        resultClasses = CountriesChart.class
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "CountriesChart")
public class CountriesChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Chart")
    private Integer id;
    @Column(name = "codChart")
    private String cod;
    @Column(name = "Nume_Judet")
    private String name;
    @Column(name = "Total_Quantity")
    private Integer quantity;
}
