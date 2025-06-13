/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.controllers.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Mortality;
import com.blacksystem.poultry_system.payload.poultryplant.request.MortalityRequest;
import com.blacksystem.poultry_system.service.MessageService;
import com.blacksystem.poultry_system.service.poultryplant.MortalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 4200)
@RequestMapping("/api/mortality")
public class MortalityController {

    private final MortalityService mortalityService;
    private final MessageService messageService;

    public MortalityController(MortalityService mortalityService, MessageService messageService) {
        this.mortalityService = mortalityService;
        this.messageService = messageService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> createMortality(@RequestBody MortalityRequest mortalityRequest) {
        try {
            return mortalityService.createMortality(mortalityRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.creation.error"));
        }
    }

    @PutMapping("/update/{idMortality}")
    public ResponseEntity<String> updateMortality(@PathVariable Long idMortality, @RequestBody MortalityRequest mortalityRequest) {
        try {
            return mortalityService.updateMortality(idMortality, mortalityRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.update.error"));
        }
    }
    @PutMapping("/{idMortality}")
    public ResponseEntity<String> deleteMortality(@PathVariable Long idMortality) {
        try {
            return mortalityService.deleteMortality(idMortality);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.deletion.error"));
        }
    }

    @PutMapping("/allmortality/{idFlock}")
    public ResponseEntity<ArrayList<Mortality>> getAllMortalityByFlock(@PathVariable Long idFlock) {
        try {
            ArrayList<Mortality> mortalities = mortalityService.getAllMortalityByFlock(idFlock);
            return ResponseEntity.ok(mortalities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}

