package com.blacksystem.poultry_system.service.manager;

import com.blacksystem.poultry_system.models.employees.Manager;
import com.blacksystem.poultry_system.repository.employee.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepo;

    public ManagerService(ManagerRepository managerRepo) {
        this.managerRepo = managerRepo;
    }
    public Manager registerManager(Manager manager) {
        return managerRepo.save(manager);
    }

    public Optional<Manager> findManager(Long id) {
        return managerRepo.findById(id);
    }

    public Manager actualizar(Long id, Manager details) {
        Manager manager = (Manager) managerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        manager.setNameEmployee(details.getNameEmployee());

        return managerRepo.save(manager);
    }

    public void borrar(Long id) {
        managerRepo.deleteById(id);
    }
}
