package com.study.Repository;

import com.study.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
    Sale findByCod(@Param("cod") String cod);
    @Query("SELECT SUM(v.quantity) FROM Sale v WHERE v.saleDate = :name")
    Integer sales(@Param("name") LocalDate date);
}