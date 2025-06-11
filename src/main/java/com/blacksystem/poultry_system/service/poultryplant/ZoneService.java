/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/10/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Zone;
import com.blacksystem.poultry_system.payload.poultryplant.ZoneRequest;
import com.blacksystem.poultry_system.repository.poultryplant.ZoneRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final MessageService messageService;

    public ZoneService(ZoneRepository zoneRepository, MessageService messageService) {
        this.zoneRepository = zoneRepository;
        this.messageService = messageService;
    }

    public ArrayList<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public ResponseEntity<String> createZone(ZoneRequest zoneRequest) {
        try {
            Zone zone = getZone(zoneRequest);

            zoneRepository.save(zone);
            String message = messageService.get("response.confirmation.creation.zone");

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String message = messageService.get("response.error.creation.zone");
            return ResponseEntity.badRequest().body(message);
        }
    }

    private static Zone getZone(ZoneRequest zoneRequest) {
        Zone zone = new Zone();

        setValues(zoneRequest, zone);
        return zone;
    }

    public ResponseEntity<String> updateZone(ZoneRequest zoneRequest, Long idZone) {
        try {
            Zone zone = getZoneById(idZone.intValue());
            if (zone != null) {
                setValues(zoneRequest, zone);
                zoneRepository.save(zone);
                String message  = messageService.get("response.confirmation.update.zone");
                return ResponseEntity.ok(message );
            } else {
                return ResponseEntity.badRequest().body(messageService.get("response.error.no.found.zone"));
            }
        } catch (Exception e) {
            String message = messageService.get("response.error.update.zone");
            return ResponseEntity.badRequest().body(message);
        }
    }

    private static void setValues(ZoneRequest zoneRequest, Zone zone) {
        zone.setManager(zoneRequest.getManager());
        zone.setZoneName(zoneRequest.getZoneName());
        zone.setCity(zoneRequest.getCity());
        zone.setFlocks(zoneRequest.getFlocks());
        zone.setStreet(zoneRequest.getStreet());
        zone.setZipCode(zoneRequest.getZipCode());
        zone.setMunicipalityName(zoneRequest.getMunicipalityName());
        zone.setZoneStatus(zoneRequest.getZoneStatus());
        zone.setPoultryHouses(zoneRequest.getPoultryHouses());
    }

    public ResponseEntity<String> deleteZone(Integer id) {
        try {
            zoneRepository.deleteById(id);
            String message = messageService.get("response.confirmation.delete.zone");

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String message = messageService.get("response.error.delete.zone");
            return ResponseEntity.badRequest().body(message);
        }
    }

    public Zone getZoneById(Integer id) {
        return zoneRepository.findById(id).orElse(null);
    }

}
