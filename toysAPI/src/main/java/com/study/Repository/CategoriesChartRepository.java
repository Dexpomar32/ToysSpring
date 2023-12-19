package com.study.Repository;

import com.study.Model.CategoriesChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesChartRepository extends JpaRepository<CategoriesChart, String> {
    @Transactional
    @Procedure(name = "UpdateCategoriesChart")
    void updateTable();
}