package org.lovepacs.repositories;

import org.lovepacs.models.Inventory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    List<Inventory> findAllByInventoryId(Integer locationId);
}
