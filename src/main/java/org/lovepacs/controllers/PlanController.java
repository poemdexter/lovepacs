package org.lovepacs.controllers;

import org.lovepacs.json.PlanBoxJson;
import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageJson;
import org.lovepacs.models.Plan;
import org.lovepacs.models.PlanBox;
import org.lovepacs.repositories.PlanBoxRepository;
import org.lovepacs.repositories.PlanRepository;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanBoxRepository planBoxRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    PlanJson getPlanById(@PathVariable final int id) {
        Plan plan = planRepository.findOne(id);
        PlanJson jsonPlan = new PlanJson();
        jsonPlan.setId(plan.getId());
        jsonPlan.setLocation(plan.getLocationId());
        jsonPlan.setPackDate(plan.getPackDate());
        jsonPlan.setEnabled(plan.getEnabled());
        List<PlanBoxJson> planBoxJsonList = new ArrayList<>();
        List<PlanBox> planBoxes = planBoxRepository.findAllByPlanId(plan.getId());
        for (PlanBox planBox : planBoxes) {
            PlanBoxJson jsonPlanBox = new PlanBoxJson();
            jsonPlanBox.setId(planBox.getId());
            jsonPlanBox.setPlanId(planBox.getPlanId());
            jsonPlanBox.setBoxId(planBox.getBoxId());
            jsonPlanBox.setQuantity(planBox.getQuantity());
            planBoxJsonList.add(jsonPlanBox);
        }
        jsonPlan.setPlanBoxes(planBoxJsonList);
        return jsonPlan;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    PlanJson createUpdatePlan(@RequestBody PlanJson planJson) {
        Plan plan = new Plan(planJson.getId(), planJson.getLocation(), planJson.getPackDate());
        Plan savedPlan = planRepository.save(plan);
        planJson.setId(savedPlan.getId());
        for(PlanBoxJson planBoxJson : planJson.getPlanBoxes()) {
            PlanBox planBox = new PlanBox(planBoxJson.getId(), planBoxJson.getPlanId(), planBoxJson.getBoxId(), planBoxJson.getQuantity());
            PlanBox savedPlanBox = planBoxRepository.save(planBox);
            planBoxJson.setId(savedPlanBox.getId());
        }
        return planJson;
    }

    @RequestMapping(value = "/{id}/complete", method = RequestMethod.POST)
    void completePlan(@PathVariable("id") final int id, @RequestBody PlanJson planJson) {
        Plan plan = new Plan(planJson.getId(), planJson.getLocation(), planJson.getPackDate());
        plan.setEnabled(false);
        planRepository.save(plan);
        planService.removePlanItemsFromInventory(planJson);
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
