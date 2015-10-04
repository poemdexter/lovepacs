package org.lovepacs.services;

import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageLocationJson;
import org.lovepacs.models.Plan;

import java.util.List;

public interface PlanService {

    void removePlanItemsFromInventory(Plan plan);

    List<ShortageLocationJson> getAllLocationShortagesForWebsite();

    ShortageLocationJson getLocationShortagesForWebsite(Integer locationId);
}
