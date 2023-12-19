package com.study.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQuery(
        name = "UpdateAgeToyCount",
        procedureName = "UpdateAgeToyCount",
        resultClasses = AgeToyChart.class
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "AgeToyCount")
public class AgeToyChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AgeToyCount")
    private Integer id;
    @Column(name = "cod")
    private String cod;
    @Column(name = "Age")
    private Integer name;
    @Column(name = "Total_Quantity")
    private Integer quantity;
}