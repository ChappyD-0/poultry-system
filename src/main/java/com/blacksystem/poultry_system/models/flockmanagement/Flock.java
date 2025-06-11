package com.blacksystem.poultry_system.models.flockmanagement;

import com.blacksystem.poultry_system.models.employees.Manager;
import com.blacksystem.poultry_system.models.poultryplant.Mortality;
import com.blacksystem.poultry_system.models.poultryplant.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_flock")
    private Long idFlock;
    @Column(name="num_flock")
    private Integer num;
    @Column(name = "date_start")
    private LocalDate dateStart;
    @Column(name = "date_finish")
    private LocalDate dateFinish;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "zone_id",nullable = false)
    private Zone flock;
    @OneToMany(
             mappedBy = "flock",
             cascade = CascadeType.ALL,
             orphanRemoval = true,
            fetch = FetchType.LAZY
     )
    private List<Mortality> mortalities = new ArrayList<>();
}
