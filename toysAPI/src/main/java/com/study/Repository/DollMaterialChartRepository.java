package com.study.Repository;

import com.study.Model.DollMaterialChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DollMaterialChartRepository extends JpaRepository<DollMaterialChart, String> {
    @Transactional
    @Procedure(name = "UpdateDollMaterial")
    void updateTable();
}