/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/2/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.repository.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.AverageWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageWeightRepoository extends JpaRepository<AverageWeight, Integer> {
}
