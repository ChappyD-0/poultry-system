/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/3/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.adminstrator;

import com.blacksystem.poultry_system.models.ERole;
import com.blacksystem.poultry_system.models.Role;
import com.blacksystem.poultry_system.models.User;
import com.blacksystem.poultry_system.models.employees.FlockKeeper;
import com.blacksystem.poultry_system.models.poultryplant.Zone;
import com.blacksystem.poultry_system.payload.employee.FlockKeeperRequest;
import com.blacksystem.poultry_system.repository.RoleRepository;
import com.blacksystem.poultry_system.repository.UserRepository;
import com.blacksystem.poultry_system.repository.employee.AdministratorRepository;
import com.blacksystem.poultry_system.repository.employee.FlockKeeperRepository;
import com.blacksystem.poultry_system.repository.poultryplant.ZoneRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final FlockKeeperRepository flockKeeperRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;


    public AdministratorService(
            AdministratorRepository adminRepo,
            FlockKeeperRepository flockKeeperRepository,
            UserRepository userRepo,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            MessageService messageService
    ) {
        this.administratorRepository = adminRepo;
        this.flockKeeperRepository = flockKeeperRepository;
        this.userRepository = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
    }

    public ResponseEntity<String> createFlockKeeper(FlockKeeperRequest request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            Role role = roleRepository.findByName(ERole.ROLE_FLOCKEERPER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);

            userRepository.save(user);

            FlockKeeper flockKeeper = new FlockKeeper();
            flockKeeper.setNameEmployee(request.getNameEmployee());
            flockKeeper.setLastName(request.getLastName());
            flockKeeper.setMiddleName(request.getMiddleName());
            flockKeeper.setBirthDate(request.getBirthDate());
            flockKeeper.setRfcEmployee(request.getRfcEmployee());
            flockKeeper.setUrlPhotoId(request.getUrlPhotoId());
            flockKeeper.setUser(user);

            flockKeeperRepository.save(flockKeeper);

            String message = messageService.get("response.confirmation.creation.flock-keeper");
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            String message = messageService.get("response.error.creation.flockkeeper");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> updateFlockKeeper(Long id, FlockKeeperRequest request) {
        try {
            Optional<FlockKeeper> optional = flockKeeperRepository.findById(id);
            if (optional.isPresent()) {
                FlockKeeper fk = optional.get();
                fk.setNameEmployee(request.getNameEmployee());
                fk.setLastName(request.getLastName());
                fk.setMiddleName(request.getMiddleName());
                fk.setBirthDate(request.getBirthDate());
                fk.setRfcEmployee(request.getRfcEmployee());
                fk.setUrlPhotoId(request.getUrlPhotoId());

                flockKeeperRepository.save(fk);
                String message = messageService.get("response.confirmation.update.flockkeeper");
                return ResponseEntity.ok(message);
            } else {
                return ResponseEntity.badRequest().body(messageService.get("response.error.no.found.flockkeeper"));
            }
        } catch (Exception e) {
            String message = messageService.get("response.error.update.flockkeeper");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> deleteFlockKeeper(Long id) {
        try {
            Optional<FlockKeeper> optional = flockKeeperRepository.findById(id);
            if (optional.isPresent()) {
                FlockKeeper fk = optional.get();
                User user = fk.getUser();

                flockKeeperRepository.delete(fk);
                if (user != null) {
                    userRepository.delete(user);
                }
                String message = messageService.get("response.confirmation.delete.flockkeeper");
                return ResponseEntity.ok(message);
            } else {
                return ResponseEntity.badRequest().body(messageService.get("response.error.no.found.flockkeeper"));
            }
        } catch (Exception e) {
            String message = messageService.get("response.error.delete.flockkeeper");
            return ResponseEntity.badRequest().body(message);
        }
    }


}