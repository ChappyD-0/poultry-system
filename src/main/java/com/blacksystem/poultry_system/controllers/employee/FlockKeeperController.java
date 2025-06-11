/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 5/29/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.controllers.employee;

import com.blacksystem.poultry_system.models.employees.FlockKeeper;
import com.blacksystem.poultry_system.payload.employee.FlockKeeperRequest;
import com.blacksystem.poultry_system.service.adminstrator.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/flockkeeper")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FlockKeeperController {

    private final AdministratorService administratorService;

    @Autowired
    public FlockKeeperController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }



}
