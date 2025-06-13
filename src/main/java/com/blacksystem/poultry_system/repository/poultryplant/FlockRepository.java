/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.repository.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Flock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlockRepository extends JpaRepository<Flock, Long> {
    // Additional query methods can be defined here if needed
}
