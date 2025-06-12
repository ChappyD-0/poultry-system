/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/11/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.AverageWeight;
import com.blacksystem.poultry_system.payload.poultryplant.request.AverageWeightRequest;
import com.blacksystem.poultry_system.repository.poultryplant.AverageWeightRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AverageWeightService {

    private final AverageWeightRepository averageWeightRepository;
    private final MessageService messageService;

    public AverageWeightService(AverageWeightRepository averageWeightRepository, MessageService messageService) {
        this.averageWeightRepository = averageWeightRepository;
        this.messageService = messageService;
    }

    public ResponseEntity<String> createAverageWeight(AverageWeightRequest averageWeightRequest) {
        try {
            AverageWeight averageWeight = new AverageWeight();
            averageWeight.setPoultryHouse(averageWeightRequest.getPoultryHouse());
            averageWeight.setFlock(averageWeightRequest.getFlock());
            averageWeight.setDate(averageWeightRequest.getDate());
            averageWeight.setCountPoultry(averageWeightRequest.getCountPoultry());
            averageWeight.setAverage(averageWeightRequest.getAverage());

            averageWeightRepository.save(averageWeight);
            return ResponseEntity.ok(messageService.get("average.weight.created"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("average.weight.creation.error"));
        }
    }

    public ResponseEntity<String> updateAverageWeight(Long idAverageWeight, AverageWeightRequest averageWeightRequest) {
        try {
            AverageWeight averageWeight = averageWeightRepository.findById(idAverageWeight)
                    .orElseThrow(() -> new RuntimeException(messageService.get("average.weight.not.found")));

            averageWeight.setPoultryHouse(averageWeightRequest.getPoultryHouse());
            averageWeight.setFlock(averageWeightRequest.getFlock());
            averageWeight.setDate(averageWeightRequest.getDate());
            averageWeight.setCountPoultry(averageWeightRequest.getCountPoultry());
            averageWeight.setAverage(averageWeightRequest.getAverage());

            averageWeightRepository.save(averageWeight);
            return ResponseEntity.ok(messageService.get("average.weight.updated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("average.weight.update.error"));
        }
    }

    public ResponseEntity<String> deleteAverageWeight(Long idAverageWeight) {
        try {
            AverageWeight averageWeight = averageWeightRepository.findById(idAverageWeight)
                    .orElseThrow(() -> new RuntimeException(messageService.get("average.weight.not.found")));

            averageWeightRepository.delete(averageWeight);
            return ResponseEntity.ok(messageService.get("average.weight.deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("average.weight.deletion.error"));
        }
    }

    public ResponseEntity<String> getAverageWeight(Long idAverageWeight) {
        try {
            AverageWeight averageWeight = averageWeightRepository.findById(idAverageWeight)
                    .orElseThrow(() -> new RuntimeException(messageService.get("average.weight.not.found")));
            return ResponseEntity.ok(messageService.get("average.weight.found") + ": " + averageWeight.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("average.weight.not.found"));
        }
    }






}
