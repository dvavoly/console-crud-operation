package com.example.controller;

import com.example.model.Developer;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;
import com.example.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    private static final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll().stream()
                .filter(e -> e.getStatus().equals(Status.ACTIVE))
                .toList();
    }
}
