package com.blacksystem.poultry_system.models.poultryplant;

import com.blacksystem.poultry_system.models.City;
import com.blacksystem.poultry_system.models.employees.Manager;
import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_zone")
    private Long idZone;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
    @Column(name="zone_name")
    private String zoneName;
    @Column(name="zone_status")
    private String zoneStatus;
    @Column(name="street_zone")
    private String street;
    @Column(name = "zipcode_zone")
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "city_id_city")
    private City city;
    @Column(name="municipality_name")
    private String municipalityName;

    @OneToMany(mappedBy = "flock",
        cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<Flock> flocks = new ArrayList<>();
    //cazetas
    @OneToMany(mappedBy = "zone",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<PoultryHouse> poultryHouses= new ArrayList<>();


}
