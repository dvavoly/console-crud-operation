package com.example.service;

import java.util.List;
import java.util.stream.Stream;

import com.example.model.Skill;
import com.example.repository.SkillRepository;

public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getById(Integer id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

    public List<Skill> createListOfSkillFromStringOfId(String strOfId) {
        return Stream.of(strOfId.split("\\s+"))
                .map(s -> skillRepository.getById(Integer.valueOf(s)))
                .toList();
    }

    public List<String> printItemsWithIndex(List<Skill> items) {
        return items.stream().map(e -> e.getId() + ":" + e.getName()).toList();
    }
}
