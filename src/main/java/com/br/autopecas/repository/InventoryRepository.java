package com.br.autopecas.repository;

import com.br.autopecas.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByProductId(Long productId);

    @Query("""
        SELECT DISTINCT i
        FROM Inventory i
        JOIN i.product p
        LEFT JOIN p.oems o
        LEFT JOIN p.crossReferences cr
        WHERE
        LOWER(p.name) LIKE LOWER(CONCAT('%', :term, '%'))
        OR LOWER(p.code) LIKE LOWER(CONCAT('%', :term, '%'))
        OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :term, '%'))
        OR LOWER(p.description) LIKE LOWER(CONCAT('%', :term, '%'))
        OR LOWER(o.oem) LIKE LOWER(CONCAT('%', :term, '%'))
        OR LOWER(cr.code) LIKE LOWER(CONCAT('%', :term, '%'))
    """)
    List<Inventory> searchProducts(@Param("term") String term);

    @Query("""
        SELECT i
        FROM Inventory i
        JOIN i.product p
        JOIN ProductVehicle pv ON pv.product.id = p.id
        WHERE pv.engine.id = :engineId
    """)
    List<Inventory> findByVehicleEngine(Long engineId);
}
