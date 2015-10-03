package org.lovepacs.repositories;

import org.lovepacs.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @version 1.0
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {


}
