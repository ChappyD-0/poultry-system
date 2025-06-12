
package com.blacksystem.poultry_system.controllers.employee;

import com.blacksystem.poultry_system.payload.employee.request.FlockKeeperRequest;
import com.blacksystem.poultry_system.payload.employee.response.FlockKeeperResponse;
import com.blacksystem.poultry_system.service.flockkeeper.FlockKeeperService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/flockkeepers")
@CrossOrigin(origins = "http://localhost:4200")
public class FlockKeeperController {

    private final FlockKeeperService svc;

    public FlockKeeperController(FlockKeeperService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<FlockKeeperResponse> create(@Valid @RequestBody FlockKeeperRequest req) {
        var resp = svc.create(req);
        return ResponseEntity
                .created(URI.create("/api/flockkeepers/" + resp.getIdEmployee()))
                .body(resp);
    }

    @GetMapping
    public ResponseEntity<List<FlockKeeperResponse>> all() {
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlockKeeperResponse> one(@PathVariable Long id) {
        return svc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlockKeeperResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody FlockKeeperRequest req
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
