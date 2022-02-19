package com.example.controller;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;
import com.example.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll().stream()
                .filter(e -> e.getStatus().equals(Status.ACTIVE))
                .toList();
    }

    public void removeDeveloper(Integer id) {
        developerRepository.deleteById(id);
    }

    public Developer getDeveloper(Integer id) {
        return developerRepository.getById(id);
    }

    public Developer updateFirstAndLastName(Developer dev, String firstName, String lastName) {
        dev.setFirstName(firstName);
        dev.setLastName(lastName);
        return developerRepository.update(dev);
    }

    public void updateSkills(Developer developer, List<Skill> skills) {
        developer.setSkills(skills);
        developerRepository.update(developer);
    }

    public void updateSpecialty(Developer developer, Specialty specialty) {
        developer.setSpecialty(specialty);
        developerRepository.update(developer);
    }
}
