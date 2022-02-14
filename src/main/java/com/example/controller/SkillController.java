package com.example.controller;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.stream.IntStream;

public class SkillController {

    private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();

    public Skill saveSkill(String name) {
        return skillRepository.save(new Skill(name));
    }

    public boolean isEmptySkillsList() {
        return skillRepository.getAll().isEmpty();
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public List<String> printSkillsWithIndex() {
        List<Skill> skills = skillRepository.getAll();
        return IntStream.range(1, skills.size())
                .mapToObj(index -> index + ":" + skills.get(index).getName())
                .toList();
    }
}
