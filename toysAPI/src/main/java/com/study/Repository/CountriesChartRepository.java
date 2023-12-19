package com.study.Repository;

import com.study.Model.CountriesChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesChartRepository extends JpaRepository<CountriesChart, String> {
    @Transactional
    @Procedure(name = "UpdateCountriesChart")
    void updateTable();
}
