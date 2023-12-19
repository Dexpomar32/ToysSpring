package com.study.Repository;

import com.study.Model.AgeToyChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeToyChartRepository extends JpaRepository<AgeToyChart, String> {
    @Transactional
    @Procedure(name = "UpdateAgeToyCount")
    void updateTable();
}
