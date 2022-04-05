package com.example.controller;

import java.util.List;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.repository.gson.GsonSpecialtyRepositoryImpl;

public class SpecialtyController {

    private static final SpecialtyRepository controller = new GsonSpecialtyRepositoryImpl();

    public List<String> printItemsWithIndex(List<Specialty> items) {
        return items.stream().map(e -> e.getId() + ":" + e.getName()).toList();
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
