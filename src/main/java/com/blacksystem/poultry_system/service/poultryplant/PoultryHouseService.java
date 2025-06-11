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
}
