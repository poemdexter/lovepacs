package org.lovepacs.controllers;

import org.lovepacs.json.InventoryJson;
import org.lovepacs.json.InventoryNeedJson;
import org.lovepacs.json.ItemNeedJson;
import org.lovepacs.json.LocationJson;
import org.lovepacs.models.Inventory;
import org.lovepacs.models.Item;
import org.lovepacs.models.Location;
import org.lovepacs.repositories.InventoryRepository;
import org.lovepacs.repositories.ItemRepository;
import org.lovepacs.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    ItemRepository itemRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Location getLocation(@PathVariable("id") final int id) {
        return locationRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Location> getAllLocations() {
        return (List<Location>)locationRepository.findAll();
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    List<InventoryNeedJson> getAllLocationInventories() {
        List<InventoryNeedJson> inventoryNeedList = new ArrayList<>();

        List<Location> locations = (List<Location>) locationRepository.findAll();
        for (Location location : locations) {
            InventoryNeedJson inventoryNeed = new InventoryNeedJson();
            inventoryNeed.setName(location.getName());

            List<ItemNeedJson> itemNeedList = new ArrayList<>();
            List<Inventory> inventories = inventoryRepository.findAllByLocationId(location.getId());
            for (Inventory inventory : inventories) {
                Item item = itemRepository.findOne(inventory.getItemId());
                itemNeedList.add(new ItemNeedJson(item.getName(), inventory.getQuantity()));
            }
            inventoryNeed.setItems(itemNeedList);
            inventoryNeedList.add(inventoryNeed);
        }

        return inventoryNeedList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableLocation(@PathVariable("id") final int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            location.setEnabled(false);
            locationRepository.save(location);
        }
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableLocation(@PathVariable("id") final int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            location.setEnabled(true);
            locationRepository.save(location);
        }
    }
}
