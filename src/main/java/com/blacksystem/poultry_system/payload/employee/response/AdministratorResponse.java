/// AdministratorResponse.java
package com.blacksystem.poultry_system.payload.employee.response;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AdministratorResponse {
    private Long   idEmployee;
    private String username;
    private String email;
    private String nameEmployee;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String rfcEmployee;
    private String urlPhotoId;
}
