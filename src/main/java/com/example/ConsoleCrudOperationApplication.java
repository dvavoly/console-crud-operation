package com.example;

import com.example.modev.Skill;
import com.example.repository.SkillRepository;

public class ConsoleCrudOperationApplication {
    public static void main(String[] args) {
        var repository = new SkillRepository();

        repository.save(new Skill("Java"));
        repository.save(new Skill("Kotlin"));
        repository.save(new Skill("JavaScript"));
        repository.save(new Skill("NodeJS"));
        repository.save(new Skill("TypeScript"));
        repository.save(new Skill("SQL"));
        repository.save(new Skill("HTML"));
        repository.save(new Skill("CSS"));

        for (Skill item : repository.getAll()) {
            System.out.println(item);
        }

        System.out.println("Update elements: ");
        System.out.println(repository.update(new Skill("HTML5", 7)));
        System.out.println();
        System.out.println("Get element by id:");
        System.out.println(repository.getById(6));
        System.out.println(repository.getById(2));
        System.out.println();
        for (Skill item : repository.getAll()) {
            System.out.println(item);
        }

        System.out.println("Delete element by id: ");
        repository.deleteById(3);
        repository.deleteById(4);
        repository.deleteById(5);
    }
}
