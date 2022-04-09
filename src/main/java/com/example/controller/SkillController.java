package com.example.controller;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.repository.jdbc.JdbcSkillRepositoryImpl;

import java.util.List;
import java.util.stream.Stream;

public class SkillController {

    //    private static final SkillRepository skillRepository = new GsonSkillRepositoryImpl();
    private static final SkillRepository skillRepository = new JdbcSkillRepositoryImpl();

    public Skill saveSkill(String name) {
        return skillRepository.save(new Skill(name));
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public List<String> printItemsWithIndex(List<Skill> items) {
        return items.stream().map(e -> e.getId() + ":" + e.getName()).toList();
    }

    public static List<Skill> createListOfSkillFromStringOfId(String strOfId) {
        return Stream.of(strOfId.split("\\s+"))
                .map(s -> skillRepository.getById(Integer.valueOf(s)))
                .toList();
    }
}
