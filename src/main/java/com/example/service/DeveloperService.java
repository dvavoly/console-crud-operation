package com.example.service;

import com.example.controller.DeveloperController;
import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getById(Integer id) {
        return developerRepository.getById(id);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll().stream()
                .filter(e -> e.getStatus().equals(Status.ACTIVE))
                .toList();
    }

    public Developer save(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer update(Developer developer) {
        return developerRepository.update(developer);
    }

    public void deleteById(Integer id) {
        developerRepository.deleteById(id);
    }

    public Developer updateFirstAndLastName(Developer dev, String firstName, String lastName, DeveloperController developerController) {
        dev.setFirstName(firstName);
        dev.setLastName(lastName);
        return update(dev);
    }

    public void updateSkills(Developer developer, List<Skill> skills, DeveloperController developerController) {
        developer.setSkills(skills);
        update(developer);
    }

    public void updateSpecialty(Developer developer, Specialty specialty, DeveloperController developerController) {
        developer.setSpecialty(specialty);
        update(developer);
    }
}
