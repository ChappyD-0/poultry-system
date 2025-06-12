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
    private String status;

    public Administrator() {
        super(); // importante si Employee tiene lógica en su constructor
    }

      public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}


}
