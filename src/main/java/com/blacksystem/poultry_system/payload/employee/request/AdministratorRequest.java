// AdministratorRequest.java
package com.blacksystem.poultry_system.payload.employee.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AdministratorRequest {
    // — datos de usuario —
    @NotBlank @Size(min=3,max=20)       private String username;
    @NotBlank @Email @Size(max=50)      private String email;
    @NotBlank @Size(min=6,max=40)       private String password;

    // — datos de empleado —
    @NotBlank                            private String nameEmployee;
    @NotBlank                            private String lastName;
    private String middleName;
    @NotNull                             private LocalDate birthDate;
    @NotBlank @Size(min=12,max=13)      private String rfcEmployee;
    @NotBlank                            private String urlPhotoId;
}
