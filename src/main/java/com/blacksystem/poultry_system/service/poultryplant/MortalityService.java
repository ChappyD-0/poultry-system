/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/11/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.Mortality;
import com.blacksystem.poultry_system.payload.poultryplant.request.MortalityRequest;
import com.blacksystem.poultry_system.repository.poultryplant.MortalityRepository;
import com.blacksystem.poultry_system.repository.poultryplant.PoultryHouseRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MortalityService {

    private final MortalityRepository mortalityRepository;
    private final MessageService messageService;
    private final PoultryHouseService poultryHouseService;
    private final

    public MortalityService(MortalityRepository mortalityRepository, MessageService messageService,
                            PoultryHouseService poultryHouseService
                            ) {
        this.mortalityRepository = mortalityRepository;
        this.messageService = messageService;
        this.poultryHouseService = poultryHouseService;
    }


    public ResponseEntity<String> createMortality(MortalityRequest mortalityRequest) {
        try {
            Mortality mortality = new Mortality();
                mortality.setPoultryHouse(mortalityRequest.getPoultryHouse());

                mortality.setFlock(mortalityRequest.getFlock());
                mortality.setDate(mortalityRequest.getDate());
                mortality.setCountMortality(mortalityRequest.getCountMortality());
                mortality.setCumulativeMortality(mortalityRequest.getCumulativeMortality());
                mortalityRepository.save(mortality);
                return  ResponseEntity.ok(messageService.get("mortality.created"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.creation.error"));
        }
    }
    public Boolean referencePoultryHouse(Long idPoultryHouse) {
        return poultryHouseService.findPoultryHouseById(idPoultryHouse);

    }


    public ResponseEntity<String> updateMortality(Long  idMortality, MortalityRequest mortalityRequest) {
        try {
            Mortality mortality = mortalityRepository.findById(idMortality)
                    .orElseThrow(() -> new RuntimeException(messageService.get("mortality.not.found")));

            mortality.setPoultryHouse(mortalityRequest.getPoultryHouse());
            mortality.setFlock(mortalityRequest.getFlock());
            mortality.setDate(mortalityRequest.getDate());
            mortality.setCountMortality(mortalityRequest.getCountMortality());
            mortality.setCumulativeMortality(mortalityRequest.getCumulativeMortality());

            mortalityRepository.save(mortality);
            return ResponseEntity.ok(messageService.get("mortality.updated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.update.error"));
        }
    }

    public ResponseEntity<String> deleteMortality(Long id) {
        try {
            Mortality mortality = mortalityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(messageService.get("mortality.not.found")));

            mortalityRepository.delete(mortality);
            return ResponseEntity.ok(messageService.get("mortality.deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(messageService.get("mortality.deletion.error"));
        }
    }

    public ResponseEntity<Mortality> getMortality(Long id) {
        try {
            Mortality mortality = mortalityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(messageService.get("mortality.not.found")));
            return ResponseEntity.ok(mortality);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



}
