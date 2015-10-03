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
    ItemRepository itemRepository;

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

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    LocationJson updateLocation(@RequestBody LocationJson locationJson) {

        if(locationJson.getId() == null) {
            // It's a create
            return createLocation(locationJson);
        }

        Location location = locationRepository.findOne(locationJson.getId());

        location.setName(locationJson.getName());
        location.setEnabled(locationJson.getEnabled());

        location = locationRepository.save(location);
        updateInventoryItems(location.getId(), locationJson.getInventory());

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
        updateInventoryItems(location.getId(), locationJson.getInventory());

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
