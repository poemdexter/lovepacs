package org.lovepacs.services;

import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageLocationJson;

import java.util.List;

public interface PlanService {

    void removePlanItemsFromInventory(PlanJson plan);

    List<ShortageLocationJson> getAllLocationShortagesForWebsite();

    ShortageLocationJson getLocationShortagesForWebsite(Integer locationId);
}
