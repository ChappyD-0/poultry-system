/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/12/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.flockmanagement.Flock;
import com.blacksystem.poultry_system.payload.poultryplant.request.FlockRequest;
import com.blacksystem.poultry_system.repository.poultryplant.FlockRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlockService {

    private final FlockRepository flockRepository;
    private final MessageService messageService;
    private final ZoneService zoneService;

    public FlockService(FlockRepository flockRepository, MessageService messageService,
                        ZoneService zoneService) {
        this.flockRepository = flockRepository;
        this.messageService = messageService;
        this.zoneService = zoneService;
    }

    public ResponseEntity<String> createFlock(FlockRequest flockRequest) {
        try {

            if(!zoneService.findZoneById(flockRequest.getIdZone())) {
                return ResponseEntity.badRequest().body(messageService.get("zone.not.found"));
            }else {

                Flock flock = new Flock();
                flock.setNum(flockRequest.getNum());
                flock.setDateFinish(flockRequest.getDateFinish());
                flock.setDateStart(flockRequest.getDateStart());
                flockRepository.save(flock);
                return ResponseEntity.ok(messageService.get("flock.created"));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("flock.creation.error"));
        }
    }

    public ResponseEntity<String> updateFlock(FlockRequest flockRequest, Long idFlock) {
        try {
            Flock flock = flockRepository.findById(idFlock)
                    .orElseThrow(() -> new RuntimeException(messageService.get("flock.not.found")));

            flock.setNum(flockRequest.getNum());
            flock.setDateFinish(flockRequest.getDateFinish());
            flock.setDateStart(flockRequest.getDateStart());



            flockRepository.save(flock);
            return ResponseEntity.ok(messageService.get("flock.updated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("flock.update.error"));
        }
    }

    public ResponseEntity<String> deleteFlock(Long idFlock) {
        try {
            Flock flock = flockRepository.findById(idFlock)
                    .orElseThrow(() -> new RuntimeException(messageService.get("flock.not.found")));

            flockRepository.delete(flock);
            return ResponseEntity.ok(messageService.get("flock.deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("flock.deletion.error"));
        }
    }

    public ResponseEntity<String> getFlock(Long idFlock) {
        try {
            Flock flock = flockRepository.findById(idFlock)
                    .orElseThrow(() -> new RuntimeException(messageService.get("flock.not.found")));
            return ResponseEntity.ok(messageService.get("flock.found") + ": " + flock.getNum());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("flock.not.found"));
        }
    }
    public Boolean findFlockById(Long idFlock) {
        return flockRepository.existsById(idFlock);
    }

}
