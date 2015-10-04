package org.lovepacs.controllers;

import org.lovepacs.json.*;
import org.lovepacs.models.*;
import org.lovepacs.repositories.*;
import org.lovepacs.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanBoxRepository planBoxRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    LocationJson getLocation(@PathVariable("id") final int id) {

        Location location = locationRepository.findOne(id);

        LocationJson locationJson = new LocationJson();

        locationJson.setId(location.getId());
        locationJson.setName(location.getName());
        locationJson.setEnabled(location.isEnabled());

        List<InventoryJson> inventoryJsons = new ArrayList<InventoryJson>();

        List<Inventory> inventoryItems = inventoryRepository.findAllByLocationId(id);

        for(Inventory inventoryItem : inventoryItems) {

            InventoryJson inventoryJson = new InventoryJson();

            inventoryJson.setId(inventoryItem.getId());
            inventoryJson.setItemId(inventoryItem.getItemId());
            inventoryJson.setQuantity(inventoryItem.getQuantity());

            inventoryJsons.add(inventoryJson);
        }

        locationJson.setInventory(inventoryJsons);

        return locationJson;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Location> getAllLocations() {
        return (List<Location>)locationRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableLocation(@PathVariable("id") final int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            location.setEnabled(false);
            locationRepository.save(location);
        }
    }

    @RequestMapping(value = "/{id}/plan", method = RequestMethod.GET)
    List<PlanJson> getPlansByLocationId(@PathVariable final int id) {
        List<PlanJson> planJsonList = new ArrayList<>();
        List<Plan> plans = planRepository.findAllByLocationIdOrderByPackDateDesc(id);
        for (Plan plan : plans) {
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
            planJsonList.add(jsonPlan);
        }
        return planJsonList;
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableLocation(@PathVariable("id") final int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            location.setEnabled(true);
            locationRepository.save(location);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    LocationJson updateLocation(@RequestBody LocationJson locationJson) {
        if(locationJson.getId() == null) {
            // It's a create
            return createLocation(locationJson);
        }

        Location location = new Location(locationJson.getName(), locationJson.getEnabled());
        location = locationRepository.save(location);

        // check to see if we're modifying inventory
        if (locationJson.getInventory() != null) {
            updateInventoryItems(location.getId(), locationJson.getInventory());
        }

        return locationJson;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    LocationJson createLocation(@RequestBody LocationJson locationJson) {
        if(locationJson.getId() != null) {
            // It's an update
            return updateLocation(locationJson);
        }

        Location location = new Location();
        location.setName(locationJson.getName());
        location.setEnabled(locationJson.getEnabled());

        location = locationRepository.save(location);
        locationJson.setId(location.getId());

        return locationJson;
    }

    private void updateInventoryItems(Integer locationId, List<InventoryJson> inventoryItems) {
        List<Inventory> currentInventory = inventoryRepository.findAllByLocationId(locationId);
        for(InventoryJson inventoryItem : inventoryItems) {
            if(inventoryItem.getId() == null) {
                // New item
                Inventory myInventory = new Inventory();
                myInventory.setItemId(inventoryItem.getItemId());
                myInventory.setQuantity(inventoryItem.getQuantity());
                myInventory.setLocationId(locationId);
                myInventory = inventoryRepository.save(myInventory);
                inventoryItem.setId(myInventory.getId());
            } else {
                // Update to an item
                Inventory myInventory = inventoryRepository.findOne(inventoryItem.getId());
                myInventory.setQuantity(inventoryItem.getQuantity());
                inventoryRepository.save(myInventory);
            }
        }
    }
}
