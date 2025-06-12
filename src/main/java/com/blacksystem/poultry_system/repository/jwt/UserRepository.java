/*
 * -----------------------------------
 *  Project: SpringSecurityApplication
 *  Author: chappyd-0
 *  Date: 5/8/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.repository.jwt;

import com.blacksystem.poultry_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}