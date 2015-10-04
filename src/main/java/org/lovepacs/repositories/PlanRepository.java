package org.lovepacs.repositories;

import org.lovepacs.models.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Integer> {

    List<Plan> findAllByLocationIdOrderByPackDateDesc(Integer locationId);
}
