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
    private String status;

    public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}