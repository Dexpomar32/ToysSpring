package com.study.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@NamedStoredProcedureQuery(
        name = "UpdateDateSales",
        procedureName = "UpdateDateSales",
        resultClasses = DateSalesChart.class
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "DateSales")
public class DateSalesChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DateSales")
    private Integer id;
    @Column(name = "cod")
    private String cod;
    @Column(name = "Date")
    private Date name;
    @Column(name = "Sales")
    private Integer quantity;
}