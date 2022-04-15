package com.example.controller;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.repository.jdbc.JdbcSpecialtyRepositoryImpl;
import com.example.service.SpecialtyService;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository = new JdbcSpecialtyRepositoryImpl();
    public SpecialtyService specialtyService = new SpecialtyService(specialtyRepository);

    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAll();
    }

    public Specialty save(Specialty specialty) {
        return specialtyService.save(specialty);
    }

    public Specialty getById(Integer id) {
        return specialtyService.getById(id);
    }

}
