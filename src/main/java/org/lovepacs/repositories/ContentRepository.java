package org.lovepacs.repositories;

import org.lovepacs.models.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @version 1.0
 */
public interface ContentRepository extends CrudRepository<Content, Integer> {

    List<Content> findAllByBoxId(Integer boxId);

}
