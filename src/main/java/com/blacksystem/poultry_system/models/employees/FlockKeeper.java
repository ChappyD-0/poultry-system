package com.blacksystem.poultry_system.models.employees;

import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import com.blacksystem.poultry_system.models.poultryplant.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("flock_Keper")
@Getter
@Setter
@AllArgsConstructor
public class FlockKeeper extends Employee{
    @OneToMany(
            mappedBy = "flockKeeper",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PoultryHouse> poultryHouses = new ArrayList<>();


    public FlockKeeper() {

    }
}
