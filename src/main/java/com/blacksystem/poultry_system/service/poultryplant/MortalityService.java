/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/11/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Mortality;
import com.blacksystem.poultry_system.payload.employee.request.MortalityRequest;
import com.blacksystem.poultry_system.repository.poultryplant.PoultryHouseRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.EntityResolver;

import javax.swing.text.html.parser.Entity;

@Service
public class MortalityService {



//    public ResponseEntity<String> createMortality(MortalityRequest mortalityRequest) {
//        try {
//
//
//            if (poultryHouseRepository.existsById(poultryHouseId)) {
//                // Logic to save mortality data
//                // For example, you might have a Mortality entity and repository
//                // Mortality mortality = new Mortality(mortalityRequest);
//                // mortalityRepository.save(mortality);
//
//                String message = "Mortality record created successfully.";
//                return ResponseEntity.ok(message);
//            } else {
//                return ResponseEntity.badRequest().body("Poultry House not found.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error creating mortality record: " + e.getMessage());
//        }
//    }



}
