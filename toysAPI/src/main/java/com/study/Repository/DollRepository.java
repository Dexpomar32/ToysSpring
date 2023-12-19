package com.study.Repository;

import com.study.Model.Doll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DollRepository extends JpaRepository<Doll, String> {
    Doll findByCod(@Param("cod") String cod);
    @Query("SELECT p FROM Doll p JOIN FETCH p.toy j ORDER BY j.price ASC")
    List<Doll> ascending();
}
