// src/main/java/com/blacksystem/poultry_system/service/flockkeeper/FlockKeeperService.java
package com.blacksystem.poultry_system.service.flockkeeper;

import com.blacksystem.poultry_system.models.ERole;
import com.blacksystem.poultry_system.models.Role;
import com.blacksystem.poultry_system.models.User;
import com.blacksystem.poultry_system.models.employees.FlockKeeper;
import com.blacksystem.poultry_system.payload.employee.request.FlockKeeperRequest;
import com.blacksystem.poultry_system.payload.employee.response.FlockKeeperResponse;
import com.blacksystem.poultry_system.repository.RoleRepository;
import com.blacksystem.poultry_system.repository.UserRepository;
import com.blacksystem.poultry_system.repository.employee.FlockKeeperRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlockKeeperService {

    private final FlockKeeperRepository repo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public FlockKeeperService(
            FlockKeeperRepository repo,
            UserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder encoder
    ) {
        this.repo     = repo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder  = encoder;
    }

    public FlockKeeperResponse create(FlockKeeperRequest req) {
        var user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        var role = roleRepo.findByName(ERole.ROLE_FLOCKEERPER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Set.of(role));
        userRepo.save(user);

        var fk = new FlockKeeper();
        fk.setUser(user);
        fk.setNameEmployee(req.getNameEmployee());
        fk.setLastName(req.getLastName());
        fk.setMiddleName(req.getMiddleName());
        fk.setBirthDate(req.getBirthDate());
        fk.setRfcEmployee(req.getRfcEmployee());
        fk.setUrlPhotoId(req.getUrlPhotoId());
        repo.save(fk);

        return map(fk);
    }

    public List<FlockKeeperResponse> findAll() {
        return repo.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Optional<FlockKeeperResponse> findById(Long id) {
        return repo.findById(id).map(this::map);
    }

    public Optional<FlockKeeperResponse> update(Long id, FlockKeeperRequest req) {
        return repo.findById(id).map(fk -> {
            fk.setNameEmployee(req.getNameEmployee());
            fk.setLastName(req.getLastName());
            fk.setMiddleName(req.getMiddleName());
            fk.setBirthDate(req.getBirthDate());
            fk.setRfcEmployee(req.getRfcEmployee());
            fk.setUrlPhotoId(req.getUrlPhotoId());
            repo.save(fk);
            return map(fk);
        });
    }

    public void delete(Long id) {
        repo.findById(id).ifPresent(fk -> {
            userRepo.delete(fk.getUser());
            repo.delete(fk);
        });
    }

    private FlockKeeperResponse map(FlockKeeper fk) {
        return new FlockKeeperResponse(
                fk.getIdEmployee(),
                fk.getUser().getUsername(),
                fk.getUser().getEmail(),
                fk.getNameEmployee(),
                fk.getLastName(),
                fk.getBirthDate()
        );
    }
}
