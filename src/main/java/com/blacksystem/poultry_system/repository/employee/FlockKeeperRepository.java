/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 5/28/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.repository.employee;

import com.blacksystem.poultry_system.models.employees.FlockKeeper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlockKeeperRepository extends JpaRepository<FlockKeeper, Long> {
    @Override
    Optional<FlockKeeper> findById(Long aLong);

    Optional<FlockKeeper> findByUser_Username(String username);
}
