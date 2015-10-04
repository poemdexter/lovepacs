package org.lovepacs.repositories;

import org.lovepacs.models.PlanBox;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanBoxRepository extends CrudRepository<PlanBox, Integer> {

    List<PlanBox> findAllByPlanId(Integer planId);

    Long deleteByPlanId(Integer planId);
}
