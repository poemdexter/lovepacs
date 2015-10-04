package org.lovepacs.repositories;

import org.lovepacs.models.PlanBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlanBoxRepository extends CrudRepository<PlanBox, Integer> {

    List<PlanBox> findAllByPlanId(Integer planId);

    @Transactional
    Long deleteByPlanId(Integer planId);
}
