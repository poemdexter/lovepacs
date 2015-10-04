package org.lovepacs.repositories;

import org.lovepacs.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findAllByLocationId(Integer locationId);

    @Modifying
    @Query("update Inventory i set i.quantity = ?3 where i.locationId = ?1 and i.itemId = ?2")
    int updateInventoryQuantities(Integer locationId, Integer itemId, Integer quantity);
}
