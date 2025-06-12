/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/11/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.payload.employee.request;

import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MortalityRequest {

    @NotNull
    private PoultryHouse poultryHouse;
    @NotNull
    private Flock flock;
    @NotNull
    private LocalDate date;
    @NotNull
    private int countMortality;
    @NotNull
    private int cumulativeMortality;
}
