/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/7/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.flockkeeper;

import com.blacksystem.poultry_system.models.employees.Administrator;
import com.blacksystem.poultry_system.repository.employee.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlockkepeerService {

    private final AdministratorRepository adminRepo;

    public FlockkepeerService(AdministratorRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Optional<Administrator> findFlckkeppepr(Long id) {
        return adminRepo.findById(id);
    }





}
