package com.study.Repository;

import com.study.Model.PopularProduct;
import com.study.Model.Toy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToyRepository extends JpaRepository<Toy, String> {
    Toy findByCod(@Param("cod") String cod);
    @Query("SELECT j FROM Toy j WHERE j.quantity = 0")
    List<Toy> quantity();
    @Query("SELECT j FROM Toy j WHERE j.country = 'Moldova'")
    List<Toy> toysMoldova();
    @Transactional
    @Procedure(name = "Exclude")
    void exclude();
    @Query("SELECT j FROM Toy j WHERE j.price = (SELECT MAX(p.price) FROM Toy p) OR j.price = (SELECT MIN(p.price) FROM Toy p)")
    List<Toy> expensiveCheap();
    @Query("SELECT AVG(j.price) FROM Toy j WHERE j.country = :country")
    Double avgPrice(@Param("country") String country);
    @Transactional
    @Procedure(name = "InsertMoldova")
    void insertMoldova();
    @Query("SELECT j FROM Toy j WHERE j.price <= :maxPrice AND j.minimAge >= :minimAge AND j.minimAge <= :maxAge")
    List<Toy> filters(@Param("maxPrice") double maxPrice, @Param("minimAge") int minimAge, @Param("maxAge") int maxAge);
    @Query("SELECT NEW com.study.Model.PopularProduct(" +
            "j.cod, j.name, j.price, j.quantity, j.country, j.minimAge, j.category, SUM(v.quantity)) " +
            "FROM Toy j JOIN Sale v ON j.id = v.toy.id " +
            "GROUP BY j.id, j.cod, j.name, j.price, j.quantity, j.country, j.minimAge, category " +
            "ORDER BY SUM(v.quantity) DESC")
    List<PopularProduct> popular();
    @Modifying
    @Transactional
    @Query("UPDATE Toy t SET t.quantity = t.quantity - :quantity WHERE t.cod = :cod")
    void updateQuantityByCod(@Param("cod") String cod, @Param("quantity") int quantity);

}