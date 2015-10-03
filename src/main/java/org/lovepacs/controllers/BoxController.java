package org.lovepacs.controllers;

import org.lovepacs.models.Box;
import org.lovepacs.repositories.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxRepository boxRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Box getBox(@PathVariable("id") final int id) {
        return boxRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Box> getAllBoxes() {
        return (List<Box>)boxRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableBox(@PathVariable("id") final int id) {
        Box box = boxRepository.findOne(id);
        if (box != null) {
            box.setEnabled(false);
            boxRepository.save(box);
        }
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableBox(@PathVariable("id") final int id) {
        Box box = boxRepository.findOne(id);
        if (box != null) {
            box.setEnabled(true);
            boxRepository.save(box);
        }
    }
}
