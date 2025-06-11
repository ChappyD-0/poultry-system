package com.blacksystem.poultry_system.models.employees;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("administrator")
@Getter
@Setter
@AllArgsConstructor
public class Administrator extends Employee{

}
