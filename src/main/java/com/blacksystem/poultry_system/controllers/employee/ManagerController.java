
package com.blacksystem.poultry_system.controllers.employee;

import com.blacksystem.poultry_system.payload.employee.request.ManagerRequest;
import com.blacksystem.poultry_system.payload.employee.response.ManagerResponse;
import com.blacksystem.poultry_system.service.manager.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/managers")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {

    private final ManagerService svc;

    public ManagerController(ManagerService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<ManagerResponse> create(@Valid @RequestBody ManagerRequest req) {
        var resp = svc.create(req);
        return ResponseEntity
                .created(URI.create("/api/managers/" + resp.getIdEmployee()))
                .body(resp);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> all() {
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponse> one(@PathVariable Long id) {
        return svc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ManagerRequest req
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
