/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/10/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import com.blacksystem.poultry_system.payload.poultryplant.PoultryHouseRequest;
import com.blacksystem.poultry_system.repository.poultryplant.PoultryHouseRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PoultryHouseService {

    private final PoultryHouseRepository poultryHouseRepository;
    private final MessageService messageService;


    public PoultryHouseService(PoultryHouseRepository poultryHouseRepository, MessageService messageService) {
        this.poultryHouseRepository = poultryHouseRepository;
        this.messageService = messageService;
    }

    public ResponseEntity<String> createPoultryHouse(PoultryHouseRequest poultryHouseRequest) {
        try {
            PoultryHouse poultryHouse = new PoultryHouse();
            poultryHouse.setIdPoultry(poultryHouseRequest.getIdPoultry());
            poultryHouse.setZone(poultryHouseRequest.getZone());
            poultryHouse.setFlockKeeper(poultryHouseRequest.getFlockKeeper());
            poultryHouse.setPoultryHouseName(poultryHouseRequest.getPoultryHouseName());
            poultryHouse.setFemaleCount(poultryHouseRequest.getFemaleCount());
            poultryHouse.setMaleCount(poultryHouseRequest.getMaleCount());

            poultryHouseRepository.save(poultryHouse);
            String message = messageService.get("response.confirmation.creation.poultryhouse");
            return ResponseEntity.ok(message);

        } catch (Exception e) {
            String message = messageService.get("response.error.creation.poultryhouse");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> updatePoultryHouse(PoultryHouseRequest poultryHouseRequest, Long idPoultryHouse) {
        try {
            PoultryHouse poultryHouse = poultryHouseRepository.findById(idPoultryHouse)
                    .orElseThrow(() -> new RuntimeException("Poultry House not found"));

            poultryHouse.setIdPoultry(poultryHouseRequest.getIdPoultry());
            poultryHouse.setZone(poultryHouseRequest.getZone());
            poultryHouse.setFlockKeeper(poultryHouseRequest.getFlockKeeper());
            poultryHouse.setPoultryHouseName(poultryHouseRequest.getPoultryHouseName());
            poultryHouse.setFemaleCount(poultryHouseRequest.getFemaleCount());
            poultryHouse.setMaleCount(poultryHouseRequest.getMaleCount());

            poultryHouseRepository.save(poultryHouse);
            String message = messageService.get("response.confirmation.update.poultryhouse");
            return ResponseEntity.ok(message);

        } catch (Exception e) {
            String message = messageService.get("response.error.update.poultryhouse");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> deletePoultryHouse(Long idPoultryHouse) {
        try {
            poultryHouseRepository.deleteById(idPoultryHouse);
            String message = messageService.get("response.confirmation.deletion.poultryhouse");
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String message = messageService.get("response.error.deletion.poultryhouse");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> getAllPoultryHouses() {
        try {
            var poultryHouses = poultryHouseRepository.findAll();
            if (poultryHouses.isEmpty()) {
                return ResponseEntity.ok(messageService.get("response.no.poultryhouses.found"));
            }
            return ResponseEntity.ok(poultryHouses.toString());
        } catch (Exception e) {
            String message = messageService.get("response.error.fetching.poultryhouses");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public ResponseEntity<String> getPoultryHouseById(Long idPoultryHouse) {
        try {
            PoultryHouse poultryHouse = poultryHouseRepository.findById(idPoultryHouse)
                    .orElseThrow(() -> new RuntimeException(messageService.get("response.no.poultryhouses.found")));
            return ResponseEntity.ok(poultryHouse.toString());
        } catch (Exception e) {
            String message = messageService.get("response.error.fetching.poultryhouse");
            return ResponseEntity.badRequest().body(message);
        }
    }
}
