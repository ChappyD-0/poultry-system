package com.blacksystem.poultry_system.controllers.poultryplant;

import com.blacksystem.poultry_system.payload.poultryplant.PoultryHouseRequest;
import com.blacksystem.poultry_system.service.poultryplant.PoultryHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poultryhouses")
@CrossOrigin(origins = "http://localhost:4200")
public class PoultryHouseController {

    @Autowired
    private PoultryHouseService poultryHouseService;

    @PostMapping
    public ResponseEntity<?> createEncargado(@RequestBody PoultryHouseRequest request) {
        return ResponseEntity.ok(poultryHouseService.create(request));
    }
}
