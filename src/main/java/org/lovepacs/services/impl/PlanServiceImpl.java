package org.lovepacs.services.impl;

import org.lovepacs.json.PlanBoxJson;
import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageJson;
import org.lovepacs.models.Inventory;
import org.lovepacs.models.Plan;
import org.lovepacs.models.PlanBox;
import org.lovepacs.repositories.InventoryRepository;
import org.lovepacs.repositories.PlanBoxRepository;
import org.lovepacs.repositories.PlanRepository;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    PlanRepository planRepository;

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
    public List<ShortageJson> getPlanShortagesByLocation(Integer locationId) {
        List<ShortageJson> shortages = new ArrayList<>();
        List<Plan> plans = planRepository.findAllByLocationIdOrderByPackDateDesc(locationId);
        Map<Integer, Shortage> globalShortageMap = new HashMap<>();
        // start with first plan (soonest) and go further away
        for (Plan plan : plans) {

            List<PlanBox> planBoxes = planBoxRepository.findAllByPlanId(plan.getId());
            Map<Integer, Shortage> planShortageMap = new HashMap<>();

            for (PlanBox planBox : planBoxes) {

                int boxesCreated = planBox.getQuantity();
                List<Map<String, Object>> results = jdbcTemplate.queryForList(removeItemsSQL, planBox.getBoxId(), plan.getLocationId());

                for (Map<String, Object> result : results) {

                    Integer itemId = (Integer) result.get("item");
                    Integer itemPerBox = (Integer) result.get("per_box");
                    Integer inventoryLeft = (Integer) result.get("inventory_left");

                    int itemsUsed = itemPerBox * boxesCreated;

                    // update plan shortage map
                    if (planShortageMap.containsKey(itemId)) {
                        planShortageMap.get(itemId).addToQuantityUsed(itemsUsed);
                    } else {
                        planShortageMap.put(itemId, new Shortage(itemId, itemsUsed, inventoryLeft));
                    }

                    // update global shortage map
                    if (globalShortageMap.containsKey(itemId)) {
                        globalShortageMap.get(itemId).addToQuantityUsed(itemsUsed);
                    } else {
                        globalShortageMap.put(itemId, new Shortage(itemId, itemsUsed, inventoryLeft));
                    }
                }
            }
        }

        // todo check shortage map

        return shortages;
    }

    private class Shortage {
        private Integer itemId;
        private Integer quantityUsed;
        private Integer inventoryAmount;

        public Shortage(Integer itemId, Integer quantityUsed, Integer inventoryAmount) {
            this.itemId = itemId;
            this.quantityUsed = quantityUsed;
            this.inventoryAmount = inventoryAmount;
        }

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public Integer getQuantityUsed() {
            return quantityUsed;
        }

        public void setQuantityUsed(Integer quantityUsed) {
            this.quantityUsed = quantityUsed;
        }

        public Integer getInventoryAmount() {
            return inventoryAmount;
        }

        public void setInventoryAmount(Integer inventoryAmount) {
            this.inventoryAmount = inventoryAmount;
        }

        public void addToQuantityUsed(Integer additional) {
            quantityUsed += additional;
        }
    }
}
