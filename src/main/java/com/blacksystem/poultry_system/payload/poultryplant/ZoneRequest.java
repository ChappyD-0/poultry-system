/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/9/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.payload.poultryplant;

import com.blacksystem.poultry_system.models.City;
import com.blacksystem.poultry_system.models.employees.Manager;
import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Setter
@Getter
public class ZoneRequest {
    @NotNull
    private Manager manager;
    @NotNull
    private String zoneName;
    @NotNull
    private String zoneStatus;
    @NotNull
    private String street;
    @NotNull
    private String zipCode;
    @NotNull
    private City city;
    @NotNull
    private String municipalityName;
    @NotNull
    private List<Flock> flocks;
    @NotNull
    private List<PoultryHouse> poultryHouses;

}
