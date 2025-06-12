// src/main/java/com/blacksystem/poultry_system/service/manager/ManagerService.java
package com.blacksystem.poultry_system.service.manager;

import com.blacksystem.poultry_system.models.ERole;
import com.blacksystem.poultry_system.models.Role;
import com.blacksystem.poultry_system.models.User;
import com.blacksystem.poultry_system.models.employees.Manager;
import com.blacksystem.poultry_system.payload.employee.request.ManagerRequest;
import com.blacksystem.poultry_system.payload.employee.response.ManagerResponse;
import com.blacksystem.poultry_system.repository.RoleRepository;
import com.blacksystem.poultry_system.repository.UserRepository;
import com.blacksystem.poultry_system.repository.employee.ManagerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    private final ManagerRepository repo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public ManagerService(
            ManagerRepository repo,
            UserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder encoder
    ) {
        this.repo    = repo;
        this.userRepo= userRepo;
        this.roleRepo= roleRepo;
        this.encoder = encoder;
    }

    public ManagerResponse create(ManagerRequest req) {
        var user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        var role = roleRepo.findByName(ERole.ROLE_MANAGER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Set.of(role));
        userRepo.save(user);

        var m = new Manager();
        m.setUser(user);
        m.setNameEmployee(req.getNameEmployee());
        m.setLastName(req.getLastName());
        m.setMiddleName(req.getMiddleName());
        m.setBirthDate(req.getBirthDate());
        m.setRfcEmployee(req.getRfcEmployee());
        m.setUrlPhotoId(req.getUrlPhotoId());
        repo.save(m);

        return map(m);
    }

    public List<ManagerResponse> findAll() {
        return repo.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Optional<ManagerResponse> findById(Long id) {
        return repo.findById(id).map(this::map);
    }

    public Optional<ManagerResponse> update(Long id, ManagerRequest req) {
        return repo.findById(id).map(m -> {
            m.setNameEmployee(req.getNameEmployee());
            m.setLastName(req.getLastName());
            m.setMiddleName(req.getMiddleName());
            m.setBirthDate(req.getBirthDate());
            m.setRfcEmployee(req.getRfcEmployee());
            m.setUrlPhotoId(req.getUrlPhotoId());
            repo.save(m);
            return map(m);
        });
    }

    public void delete(Long id) {
        repo.findById(id).ifPresent(m -> {
            userRepo.delete(m.getUser());
            repo.delete(m);
        });
    }

    private ManagerResponse map(Manager m) {
        return new ManagerResponse(
                m.getIdEmployee(),
                m.getUser().getUsername(),
                m.getUser().getEmail(),
                m.getNameEmployee(),
                m.getLastName()
        );
    }
}
