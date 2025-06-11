package com.blacksystem.poultry_system.models.employees;

import com.blacksystem.poultry_system.models.poultryplant.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("manager")
@Getter
@Setter
@AllArgsConstructor
public class Manager  extends Employee{
    @OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Zone> zones = new ArrayList<>();


    public Manager() {

    }
}
