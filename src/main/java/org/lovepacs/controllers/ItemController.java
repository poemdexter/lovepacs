package org.lovepacs.controllers;

import org.lovepacs.models.Box;
import org.lovepacs.models.Item;
import org.lovepacs.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    Item getItem(@PathVariable("id") final int id) {
        return itemRepository.findOne(id);
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    List<Item> getAllItems() {
        return (List<Item>)itemRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteBox(@PathVariable("id") final int id) {
        Item item = itemRepository.findOne(id);
        if (item != null) {
            item.setEnabled(false);
            itemRepository.save(item);
        }
    }
}
