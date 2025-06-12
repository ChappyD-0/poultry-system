
package com.blacksystem.poultry_system.controllers.employee;

import com.blacksystem.poultry_system.payload.employee.request.AdministratorRequest;
import com.blacksystem.poultry_system.payload.employee.response.AdministratorResponse;
import com.blacksystem.poultry_system.service.adminstrator.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/administrators")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministratorController {

    private final AdministratorService svc;

    public AdministratorController(AdministratorService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<AdministratorResponse> create(@Valid @RequestBody AdministratorRequest req) {
        var resp = svc.create(req);
        return ResponseEntity
                .created(URI.create("/api/administrators/" + resp.getIdEmployee()))
                .body(resp);
    }

    @GetMapping
    public ResponseEntity<List<AdministratorResponse>> all() {
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponse> one(@PathVariable Long id) {
        return svc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AdministratorRequest req
    ) {
        return svc.update(id, req)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
