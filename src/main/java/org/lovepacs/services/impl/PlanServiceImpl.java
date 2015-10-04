package org.lovepacs.services.impl;

import org.lovepacs.models.Inventory;
import org.lovepacs.models.Plan;
import org.lovepacs.repositories.InventoryRepository;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    private String removeItemsSQL = "select c.item, c.quantity as created, i.quantity as inventory from contents c, inventory i where i.item = c.item and c.box = ? and i.location = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public void removePlanItemsFromInventory(Plan plan) {
        int boxesCreated = plan.getQuantity();
        List<Map<String, Object>> results = jdbcTemplate.queryForList(removeItemsSQL, plan.getBoxId(), plan.getLocationId());
        for(Map<String, Object> result : results) {
            Integer itemId = (Integer) result.get("item");
            Integer itemPerBox = (Integer) result.get("per_box");
            Integer inventoryLeft = (Integer) result.get("inventory_left");

            int itemsUsed = itemPerBox * boxesCreated;
            int newInventory = inventoryLeft - itemsUsed;

            inventoryRepository.save(new Inventory(plan.getLocationId(), itemId, newInventory));
        }
    }

    @Override
    public void getPlanShortages(Plan plan) {

        int boxesCreated = plan.getQuantity();
        List<Map<String, Object>> results = jdbcTemplate.queryForList(removeItemsSQL, plan.getBoxId(), plan.getLocationId());
        for(Map<String, Object> result : results) {
            Integer itemId = (Integer) result.get("item");
            Integer itemPerBox = (Integer) result.get("per_box");
            Integer inventoryLeft = (Integer) result.get("inventory_left");

            int itemsUsed = itemPerBox * boxesCreated;
            int newInventory = inventoryLeft - itemsUsed;

            inventoryRepository.save(new Inventory(plan.getLocationId(), itemId, newInventory));
        }
    }
}
