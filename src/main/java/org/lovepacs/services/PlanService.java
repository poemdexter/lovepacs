package org.lovepacs.services;

import org.lovepacs.json.PlanJson;
import org.lovepacs.json.ShortageJson;
import org.lovepacs.models.Plan;

import java.util.List;

public interface PlanService {

    void removePlanItemsFromInventory(PlanJson plan);

    List<ShortageJson> getPlanShortages(Plan plan);
}
