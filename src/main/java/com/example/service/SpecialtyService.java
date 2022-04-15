package com.example.service;

import com.example.controller.SpecialtyController;
import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;

import java.util.List;


public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public Specialty getById(Integer id) {
        return specialtyRepository.getById(id);
    }

    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public Specialty update(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    public void deleteById(Integer id) {
        specialtyRepository.deleteById(id);
    }

    public List<String> getAllSpecialtyWithId(SpecialtyController specialtyController) {
        return specialtyController.getAllSpecialties().stream().map(e -> e.getId() + ":" + e.getName()).toList();
    }
}
