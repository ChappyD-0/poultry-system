package com.blacksystem.poultry_system.service.poultryplant;

import com.blacksystem.poultry_system.models.poultryplant.PoultryHouse;
import com.blacksystem.poultry_system.payload.poultryplant.PoultryHouseRequest;
import com.blacksystem.poultry_system.repository.poultryplant.PoultryHouseRepository;
import com.blacksystem.poultry_system.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoultryHouseService {

    private final PoultryHouseRepository poultryHouseRepository;
    private final MessageService messageService;

    public PoultryHouseService(PoultryHouseRepository poultryHouseRepository, MessageService messageService) {
        this.poultryHouseRepository = poultryHouseRepository;
        this.messageService = messageService;
    }

    public PoultryHouse create(PoultryHouseRequest request) {
        PoultryHouse house = new PoultryHouse();
        house.setPoultryHouseName(request.getPoultryHouseName());
        house.setZone(request.getZone());
        house.setFlockKeeper(request.getFlockKeeper());
        house.setFemaleCount(request.getFemaleCount());
        house.setMaleCount(request.getMaleCount());
        return poultryHouseRepository.save(house);
    }

    public PoultryHouse update(Long id, PoultryHouseRequest request) {
        PoultryHouse house = poultryHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PoultryHouse no encontrada con ID: " + id));
        house.setPoultryHouseName(request.getPoultryHouseName());
        house.setZone(request.getZone());
        house.setFlockKeeper(request.getFlockKeeper());
        house.setFemaleCount(request.getFemaleCount());
        house.setMaleCount(request.getMaleCount());
        return poultryHouseRepository.save(house);
    }

    public void delete(Long id) {
        poultryHouseRepository.deleteById(id);
    }

    public List<PoultryHouse> findAll() {
        return poultryHouseRepository.findAll();
    }

    public PoultryHouse findById(Long id) {
        return poultryHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PoultryHouse no encontrada con ID: " + id));
    }
}
