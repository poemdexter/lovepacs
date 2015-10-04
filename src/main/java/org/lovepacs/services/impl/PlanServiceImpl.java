package org.lovepacs.services.impl;

import org.lovepacs.json.PlanBoxJson;
import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageItemJson;
import org.lovepacs.json.ShortageLocationJson;
import org.lovepacs.models.*;
import org.lovepacs.repositories.*;
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

    private static final String ITEM_USAGE_SQL = "select c.item, c.quantity as used_per_box, i.quantity as inventory from contents c, inventory i where i.item = c.item and c.box = ? and i.location = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    PlanBoxRepository planBoxRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void removePlanItemsFromInventory(PlanJson plan) {
        for (PlanBoxJson planBoxJson : plan.getPlanBoxes()) {
            int boxesCreated = planBoxJson.getQuantity();
            List<Map<String, Object>> results = jdbcTemplate.queryForList(ITEM_USAGE_SQL, planBoxJson.getBoxId(), plan.getLocation());
            for(Map<String, Object> result : results) {
                Integer itemId = (Integer) result.get("item");
                Integer itemPerBox = (Integer) result.get("used_per_box");
                Integer inventoryLeft = (Integer) result.get("inventory");

                int itemsUsed = itemPerBox * boxesCreated;
                int newInventory = inventoryLeft - itemsUsed;

                inventoryRepository.save(new Inventory(plan.getLocation(), itemId, newInventory));
            }
        }
    }

    @Override
    public List<ShortageLocationJson> getAllLocationShortagesForWebsite() {
        List<ShortageLocationJson> shortages = new ArrayList<>();

        List<Location> locations = locationRepository.findLocationByEnabled(true);

        // for each location, get all plans...
        for (Location location : locations) {

            List<Plan> plans = planRepository.findAllByLocationIdOrderByPackDateDesc(location.getId());
            Map<Integer, Shortage> locationShortageMap = new HashMap<>();

            // for each plan, get all plan boxes...
            for (Plan plan : plans) {

                List<PlanBox> planBoxes = planBoxRepository.findAllByPlanId(plan.getId());

                // for each planBox, get total used items and add to map
                for (PlanBox planBox : planBoxes) {

                    int boxesCreated = planBox.getQuantity();
                    List<Map<String, Object>> results = jdbcTemplate.queryForList(ITEM_USAGE_SQL, planBox.getBoxId(), location.getId());

                    for (Map<String, Object> result : results) {

                        Integer itemId = (Integer) result.get("item");
                        Integer itemPerBox = (Integer) result.get("used_per_box");
                        Integer inventoryLeft = (Integer) result.get("inventory");

                        int itemsUsed = itemPerBox * boxesCreated;

                        // update location shortage map
                        if (locationShortageMap.containsKey(itemId)) {
                            locationShortageMap.get(itemId).addToQuantityUsed(itemsUsed);
                        } else {
                            locationShortageMap.put(itemId, new Shortage(itemId, itemsUsed, inventoryLeft));
                        }
                    }
                }
            }

            // go through shortage map and calculate any item shortages
            List<ShortageItemJson> shortageItemJsonList = new ArrayList<>();
            for (Map.Entry<Integer,Shortage> entry : locationShortageMap.entrySet()) {
                Integer itemId = entry.getKey();
                Shortage shortage = entry.getValue();

                if (shortage.getQuantityUsed() > shortage.getInventoryAmount()) {
                    Item item = itemRepository.findOne(itemId);
                    ShortageItemJson shortageItemJson = new ShortageItemJson(item.getName(), shortage.getQuantityUsed() - shortage.getInventoryAmount());
                    shortageItemJsonList.add(shortageItemJson);
                }
            }

            // if we have shortages, create a new locationShortage and add to list
            if (shortageItemJsonList.size() > 0) {
                ShortageLocationJson shortage = new ShortageLocationJson(location.getName(), shortageItemJsonList);
                shortages.add(shortage);
            }
        }

        return shortages;
    }

    public ShortageLocationJson getLocationShortagesForWebsite(Integer locationId) {
        Location location = locationRepository.findOne(locationId);
        List<Plan> plans = planRepository.findAllByLocationIdOrderByPackDateDesc(location.getId());
        Map<Integer, Shortage> locationShortageMap = new HashMap<>();

        // for each plan, get all plan boxes...
        for (Plan plan : plans) {

            List<PlanBox> planBoxes = planBoxRepository.findAllByPlanId(plan.getId());

            // for each planBox, get total used items and add to map
            for (PlanBox planBox : planBoxes) {

                int boxesCreated = planBox.getQuantity();
                List<Map<String, Object>> results = jdbcTemplate.queryForList(ITEM_USAGE_SQL, planBox.getBoxId(), location.getId());

                for (Map<String, Object> result : results) {

                    Integer itemId = (Integer) result.get("item");
                    Integer itemPerBox = (Integer) result.get("used_per_box");
                    Integer inventoryLeft = (Integer) result.get("inventory");

                    int itemsUsed = itemPerBox * boxesCreated;

                    // update location shortage map
                    if (locationShortageMap.containsKey(itemId)) {
                        locationShortageMap.get(itemId).addToQuantityUsed(itemsUsed);
                    } else {
                        locationShortageMap.put(itemId, new Shortage(itemId, itemsUsed, inventoryLeft));
                    }
                }
            }
        }

        // go through shortage map and calculate any item shortages
        List<ShortageItemJson> shortageItemJsonList = new ArrayList<>();
        for (Map.Entry<Integer,Shortage> entry : locationShortageMap.entrySet()) {
            Integer itemId = entry.getKey();
            Shortage shortage = entry.getValue();

            if (shortage.getQuantityUsed() > shortage.getInventoryAmount()) {
                Item item = itemRepository.findOne(itemId);
                ShortageItemJson shortageItemJson = new ShortageItemJson(item.getName(), shortage.getQuantityUsed() - shortage.getInventoryAmount());
                shortageItemJsonList.add(shortageItemJson);
            }
        }

        return new ShortageLocationJson(location.getName(), shortageItemJsonList);
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
