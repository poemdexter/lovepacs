package org.lovepacs.controllers;

import org.lovepacs.models.Plan;
import org.lovepacs.repositories.PlanRepository;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @Autowired
    PlanRepository planRepository;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    Plan createUpdatePlan(@RequestBody Plan plan) {
        return planRepository.save(plan);
    }

    @RequestMapping(value = "/{id}/complete", method = RequestMethod.POST)
    void completePlan(@PathVariable("id") final int id, @RequestBody Plan plan) {
        plan.setEnabled(false);
        planRepository.save(plan);

        planService.removePlanItemsFromInventory(plan);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableBox(@PathVariable("id") final int id) {
        Plan plan = planRepository.findOne(id);
        if (plan != null) {
            plan.setEnabled(false);
            planRepository.save(plan);
        }
    }
}
