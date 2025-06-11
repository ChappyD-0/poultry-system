/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 5/29/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.payload.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FlockKeeperRequest {
    // ——— data for user ———
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank @Size(max = 50) @Email
    private String email;

    @NotBlank @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private Set<String> role;

    // ——— data for Employee ———
    @NotBlank
    private String nameEmployee;

    @NotBlank
    private String lastName;

    private String middleName;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String urlPhotoId;

    @NotBlank
    @Size(min = 12, max = 13)
    @NotNull
    private String rfcEmployee;

}
