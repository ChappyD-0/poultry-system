package com.blacksystem.poultry_system.models.poultryplant;

import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mortality {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_mortality")
    private long idMortality;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "poultry_house_id")
    private PoultryHouse poultryHouse;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="Flock_id")
    private Flock flock;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "count_mortality")
    private int countMortality;
    @Column(name = "cumulative_mortality")
    private int cumulativeMortality;
}
