package org.lovepacs.repositories;

import org.lovepacs.models.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    List<Inventory> findAllByLocationId(Integer locationId);

    @Modifying
    @Query("update inventory i set i.quantity = ?3 where i.location = ?1 and i.item = ?2")
    Long updateInventoryQuantities(Integer locationId, Integer itemId, Integer quantity);
}
