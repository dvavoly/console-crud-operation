package com.example;

import com.example.controller.DeveloperController;
import com.example.modev.Skill;
import com.example.repository.SkillRepository;

import java.util.Scanner;

public class ConsoleCrudOperationApplication {
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var controller = new DeveloperController(scanner);
            controller.start();
        }
    }
}
