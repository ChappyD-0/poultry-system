package com.blacksystem.poultry_system.models.poultryplant;

import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AverageWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_averegeWeight")
    private Long idAverageWeight;
    //Kazeta
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "poultry_house_id")
    private PoultryHouse poultryHouse;
    //Parvada
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="Flock_id")
    private Flock flock;
    @Column(name = "average")
    private float average;
    @Column(name = "count_poultry")
    private int countPoultry;
    @Column(name = "date_average")
    private Date date;
}