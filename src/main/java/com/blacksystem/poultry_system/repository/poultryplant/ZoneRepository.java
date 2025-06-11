/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/2/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.repository.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ZoneRepository extends CrudRepository<Zone, Integer> {

    public ArrayList<Zone> findAll();

    boolean getByIdZone(Long idZone);
}
