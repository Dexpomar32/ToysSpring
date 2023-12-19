package com.study.Repository;

import com.study.Model.DateSalesChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DateSalesChartRepository extends JpaRepository<DateSalesChart, String> {
    @Transactional
    @Procedure(name = "UpdateDateSales")
    void updateTable();
}
