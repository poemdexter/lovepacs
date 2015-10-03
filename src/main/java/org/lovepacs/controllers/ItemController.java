package org.lovepacs.controllers;

import org.lovepacs.json.ItemJson;
import org.lovepacs.models.Item;
import org.lovepacs.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Item getItem(@PathVariable("id") final int id) {
        return itemRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Item> getAllItems() {
        return (List<Item>)itemRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableItem(@PathVariable("id") final int id) {
        Item item = itemRepository.findOne(id);
        if (item != null) {
            item.setEnabled(false);
            itemRepository.save(item);
        }
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableItem(@PathVariable("id") final int id) {
        Item item = itemRepository.findOne(id);
        if (item != null) {
            item.setEnabled(true);
            itemRepository.save(item);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    void createItem(@RequestBody ItemJson itemJson) {

        if(itemJson.getId() != null) {
            // It's an update
            updateItem(itemJson);
            return;
        }

        Item item = new Item();
        item.setName(itemJson.getName());
        item.setEnabled(itemJson.getEnabled());
        item.setPrice(itemJson.getPrice());

        item  = itemRepository.save(item);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    void updateItem(@RequestBody ItemJson itemJson) {

        if(itemJson.getId() == null) {
            // It's a create
            createItem(itemJson);
            return;
        }

        Item item = itemRepository.findOne(itemJson.getId());
        item.setName(itemJson.getName());
        item.setEnabled(itemJson.getEnabled());
        item.setPrice(itemJson.getPrice());

        itemRepository.save(item);
    }


}
