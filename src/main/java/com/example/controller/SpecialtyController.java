package com.example.controller;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.repository.jdbc.JdbcSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {

    //    private static final SpecialtyRepository controller = new GsonSpecialtyRepositoryImpl();
    private static final SpecialtyRepository controller = new JdbcSpecialtyRepositoryImpl();

    public List<String> getAllSpecialtyWithId() {
        return getAllSpecialties().stream().map(e -> e.getId() + ":" + e.getName()).toList();
    }

    public List<Specialty> getAllSpecialties() {
        return controller.getAll();
    }

    public Specialty save(Specialty specialty) {
        return controller.save(specialty);
    }

    public Specialty getById(Integer id) {
        return controller.getById(id);
    }

}
