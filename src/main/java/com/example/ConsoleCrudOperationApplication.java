package com.example;

import com.example.modev.Skill;
import com.example.repository.SkillRepository;

public class ConsoleCrudOperationApplication {
    public static void main(String[] args) {
        SkillRepository repository = new SkillRepository();
        Skill skill = new Skill("Scala");
        repository.save(skill);
        skill = new Skill("Node");
        repository.save(skill);

        for (Skill item : repository.getAll()) {
            System.out.println(item);
        }
    }
}
