/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.payload.poultryplant.request;

import com.blacksystem.poultry_system.models.poultryplant.Flock;
import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AverageWeightRequest {
    //Kazeta
    @NotNull
    private PoultryHouse poultryHouse;
    //Parvada
    @NotNull
    private Flock flock;
    @NotNull
    private float average;
    @NotNull
    private int countPoultry;
    @NotNull
    private Date date;
}
