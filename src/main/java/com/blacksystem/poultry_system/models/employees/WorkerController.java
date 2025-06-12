package com.blacksystem.poultry_system.models.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blacksystem.poultry_system.service.adminstrator.AdministratorService;
import com.blacksystem.poultry_system.service.flockkeeper.FlockKeeperService;
import com.blacksystem.poultry_system.service.manager.ManagerService;


@RestController
@RequestMapping("/api/workers")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkerController {

    @Autowired
    private FlockKeeperService flockKeeperService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllWorkers() {
        List<Map<String, Object>> all = new ArrayList<>();

        flockKeeperService.findAll().forEach(fk -> {
            Map<String, Object> data = new HashMap<>();
            data.put("id", fk.getIdEmployee());
            data.put("name", fk.getNameEmployee());
            data.put("category", "GRANJERO");
            data.put("status", fk.getStatus());
            all.add(data);
        });

        managerService.findAll().forEach(mg -> {
            Map<String, Object> data = new HashMap<>();
            data.put("id", mg.getIdEmployee());
            data.put("name", mg.getNameEmployee());
            data.put("category", "ENCARGADO");
            data.put("status", mg.getStatus());
            all.add(data);
        });

        administratorService.findAll().forEach(ad -> {
            Map<String, Object> data = new HashMap<>();
            data.put("id", ad.getIdEmployee());
            data.put("name", ad.getNameEmployee());
            data.put("category", "ADMINISTRADOR");
            data.put("status", ad.getStatus());
            all.add(data);
        });

        return ResponseEntity.ok(all);
    }
}