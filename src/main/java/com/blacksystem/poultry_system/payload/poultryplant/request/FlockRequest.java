/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.payload.poultryplant.request;

import com.blacksystem.poultry_system.models.poultryplant.Mortality;
import com.blacksystem.poultry_system.models.poultryplant.Zone;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlockRequest {
    @NotNull
    private Long idFlock;
    @NotNull
    private Integer num;
    @NotNull
    private LocalDate dateStart;
    @NotNull
    private LocalDate dateFinish;
    @NotNull
    private Long idZone;

}
