// ManagerResponse.java
package com.blacksystem.poultry_system.payload.employee.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ManagerResponse {
    private Long   idEmployee;
    private String username;
    private String email;
    private String nameEmployee;
    private String lastName;
     private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
