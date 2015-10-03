package org.lovepacs.controllers;

import org.lovepacs.models.Location;
import org.lovepacs.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Location getLocation(@PathVariable("id") final int id) {
        return locationRepository.findOne(id);
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

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableLocation(@PathVariable("id") final int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            location.setEnabled(true);
            locationRepository.save(location);
        }
    }
}
