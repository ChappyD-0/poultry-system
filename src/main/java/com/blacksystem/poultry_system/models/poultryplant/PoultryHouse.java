package com.blacksystem.poultry_system.models.poultryplant;

import com.blacksystem.poultry_system.models.employees.FlockKeeper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoultryHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poultry")
    private Long idPoultry;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "poultry_zone")
    private Zone zone;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_Keeper_id")
    private FlockKeeper flockKeeper;
    @Column(name="poultry_house_name")
    private String poultryHouseName;
    @Column(name = "female_Count")
    private int femaleCount;
    @Column(name = "male_count")
    private int  maleCount;

}
