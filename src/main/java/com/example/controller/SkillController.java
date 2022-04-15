package com.example.controller;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.repository.jdbc.JdbcSkillRepositoryImpl;
import com.example.service.SkillService;

import java.util.List;

public class SkillController {

    private final SkillRepository skillRepository = new JdbcSkillRepositoryImpl();
    public final SkillService skillService = new SkillService(skillRepository);

    public Skill saveSkill(String name) {
        return skillService.save(new Skill(name));
    }

    public List<Skill> getAllSkills() {
        return skillService.getAll();
    }

}
