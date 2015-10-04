/*
 * *********************************************************************
 *  Copyright(c) 2015 e-Rewards, Inc. All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * *********************************************************************
 */

package org.lovepacs.repositories;

import org.lovepacs.models.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    List<Location> findLocationByEnabled(Boolean enabled);
}
