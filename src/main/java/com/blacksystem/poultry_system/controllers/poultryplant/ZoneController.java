/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.controllers.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Zone;
import com.blacksystem.poultry_system.payload.poultryplant.request.ZoneRequest;
import com.blacksystem.poultry_system.service.MessageService;
import com.blacksystem.poultry_system.service.poultryplant.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 4200)
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;
    private final MessageService messageService;
    public ZoneController(ZoneService zoneService, MessageService messageService) {
        this.zoneService = zoneService;
        this.messageService = messageService;
    }

    @PutMapping("/register")
    public ResponseEntity<String> createZone(@RequestBody ZoneRequest zoneRequest) {
        try {
            return  zoneService.createZone(zoneRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("zone.created.error"));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateZone(@RequestBody ZoneRequest zoneRequest, @PathVariable Long id) {
        try {
            return zoneService.updateZone(zoneRequest, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("zone.updated.error"));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> deleteZone(@PathVariable Long id) {
        try {
            return zoneService.deleteZone(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("zone.deleted.error"));
        }
    }
    @GetMapping("/all")
    public Object getAllZones() {
        try {
            return ResponseEntity.ok(zoneService.getAllZones()).getBody();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("zone.list.error"));
        }
    }
    @PutMapping("/getzone/{id}")
    private Zone getZoneById(Long idZone) {
        return zoneService.getZoneById(idZone);
    }


}
