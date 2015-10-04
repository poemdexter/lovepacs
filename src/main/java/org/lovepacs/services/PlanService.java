package org.lovepacs.services;

import org.lovepacs.json.ShortageJson;
import org.lovepacs.models.Plan;

import java.util.List;

public interface PlanService {

    void removePlanItemsFromInventory(Plan plan);

    List<ShortageJson> getPlanShortages(Plan plan);
}
