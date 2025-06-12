package com.blacksystem.poultry_system.payload.employee.response;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FlockKeeperResponse {
    private Long   idEmployee;
    private String username;
    private String email;
    private String nameEmployee;
    private String lastName;
    private LocalDate birthDate;
}