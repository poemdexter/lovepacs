package org.lovepacs.services.impl;

import org.lovepacs.json.PlanBoxJson;
import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageJson;
import org.lovepacs.models.Inventory;
import org.lovepacs.models.Plan;
import org.lovepacs.models.PlanBox;
import org.lovepacs.repositories.InventoryRepository;
import org.lovepacs.repositories.PlanBoxRepository;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    private String removeItemsSQL = "select c.item, c.quantity as created, i.quantity as inventory from contents c, inventory i where i.item = c.item and c.box = ? and i.location = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    PlanBoxRepository planBoxRepository;

    @Override
    public void removePlanItemsFromInventory(PlanJson plan) {
        for (PlanBoxJson planBoxJson : plan.getPlanBoxes()) {
            int boxesCreated = planBoxJson.getQuantity();
            List<Map<String, Object>> results = jdbcTemplate.queryForList(removeItemsSQL, planBoxJson.getBoxId(), plan.getLocation());
            for(Map<String, Object> result : results) {
                Integer itemId = (Integer) result.get("item");
                Integer itemPerBox = (Integer) result.get("per_box");
                Integer inventoryLeft = (Integer) result.get("inventory_left");

                int itemsUsed = itemPerBox * boxesCreated;
                int newInventory = inventoryLeft - itemsUsed;

                inventoryRepository.save(new Inventory(plan.getLocation(), itemId, newInventory));
            }
        }
    }

    @Override
    public List<ShortageJson> getPlanShortages(Plan plan) {
        List<ShortageJson> shortages = new ArrayList<>();
        List<PlanBox> planBoxes = planBoxRepository.findAllByPlanId(plan.getId());
        for (PlanBox planBox : planBoxes) {
            int boxesCreated = planBox.getQuantity();
            List<Map<String, Object>> results = jdbcTemplate.queryForList(removeItemsSQL, planBox.getBoxId(), plan.getLocationId());
            for(Map<String, Object> result : results) {
                Integer itemId = (Integer) result.get("item");
                Integer itemPerBox = (Integer) result.get("per_box");
                Integer inventoryLeft = (Integer) result.get("inventory_left");

                int itemsUsed = itemPerBox * boxesCreated;
                int newInventory = inventoryLeft - itemsUsed;

                if (newInventory < 0) {
                    // todo shortage!
                }
            }
        }
        return shortages;
    }
}
