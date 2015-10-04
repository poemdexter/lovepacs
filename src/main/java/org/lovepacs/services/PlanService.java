package org.lovepacs.services;

import org.lovepacs.models.Plan;

public interface PlanService {

    void removePlanItemsFromInventory(Plan plan);

    void getPlanShortages(Plan plan);
}
