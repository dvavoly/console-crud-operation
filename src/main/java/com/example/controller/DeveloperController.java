package com.example.controller;

import com.example.model.Developer;
import com.example.repository.DeveloperRepository;
import com.example.repository.jdbc.JdbcDeveloperRepositoryImpl;
import com.example.service.DeveloperService;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new JdbcDeveloperRepositoryImpl();

    public final DeveloperService developerService = new DeveloperService(developerRepository);

    public Developer saveDeveloper(Developer developer) {
        return developerService.save(developer);
    }

    public List<Developer> getAllDevelopers() {
        return developerService.getAll();
    }

    public void removeDeveloper(Integer id) {
        developerService.deleteById(id);
    }

    public Developer getDeveloper(Integer id) {
        return developerService.getById(id);
    }

}
