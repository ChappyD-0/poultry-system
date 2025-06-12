// src/main/java/com/blacksystem/poultry_system/service/administrator/AdministratorService.java
package com.blacksystem.poultry_system.service.adminstrator;

import com.blacksystem.poultry_system.models.ERole;
import com.blacksystem.poultry_system.models.Role;
import com.blacksystem.poultry_system.models.User;
import com.blacksystem.poultry_system.models.employees.Administrator;
import com.blacksystem.poultry_system.payload.employee.request.AdministratorRequest;
import com.blacksystem.poultry_system.payload.employee.response.AdministratorResponse;
import com.blacksystem.poultry_system.repository.RoleRepository;
import com.blacksystem.poultry_system.repository.UserRepository;
import com.blacksystem.poultry_system.repository.employee.AdministratorRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdministratorService {

    private final AdministratorRepository repo;
    private final UserRepository      userRepo;
    private final RoleRepository      roleRepo;
    private final PasswordEncoder     encoder;
    private final MessageService      msgs;

    public AdministratorService(
            AdministratorRepository repo,
            UserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder encoder,
            MessageService msgs
    ) {
        this.repo     = repo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder  = encoder;
        this.msgs     = msgs;
    }

    public AdministratorResponse create(AdministratorRequest req) {
        var user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        var role = roleRepo.findByName(ERole.ROLE_ADMINISTRATOR)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Set.of(role));
        userRepo.save(user);

        var admin = new Administrator();
        admin.setUser(user);
        admin.setNameEmployee(req.getNameEmployee());
        admin.setLastName(req.getLastName());
        admin.setMiddleName(req.getMiddleName());
        admin.setBirthDate(req.getBirthDate());
        admin.setRfcEmployee(req.getRfcEmployee());
        admin.setUrlPhotoId(req.getUrlPhotoId());
        repo.save(admin);

        return map(admin);
    }

    public List<AdministratorResponse> findAll() {
        return repo.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Optional<AdministratorResponse> findById(Long id) {
        return repo.findById(id).map(this::map);
    }

    public Optional<AdministratorResponse> update(Long id, AdministratorRequest req) {
        return repo.findById(id).map(admin -> {
            admin.setNameEmployee(req.getNameEmployee());
            admin.setLastName(req.getLastName());
            admin.setMiddleName(req.getMiddleName());
            admin.setBirthDate(req.getBirthDate());
            admin.setRfcEmployee(req.getRfcEmployee());
            admin.setUrlPhotoId(req.getUrlPhotoId());
            repo.save(admin);
            return map(admin);
        });
    }

    public void delete(Long id) {
        repo.findById(id).ifPresent(admin -> {
            userRepo.delete(admin.getUser());
            repo.delete(admin);
        });
    }

    private AdministratorResponse map(Administrator a) {
        return new AdministratorResponse(
                a.getIdEmployee(),
                a.getUser().getUsername(),
                a.getUser().getEmail(),
                a.getNameEmployee(),
                a.getLastName(),
                a.getMiddleName(),
                a.getBirthDate(),
                a.getRfcEmployee(),
                a.getUrlPhotoId()
        );
    }
}
