package com.example.view;

import com.example.controller.DeveloperController;
import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.utils.Messages;

import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private final SkillView skillView = new SkillView();
    private final SpecialtyView specialtyView = new SpecialtyView();
    private final DeveloperController controller = new DeveloperController();

    public void createDeveloper(Scanner scanner) {
        System.out.println(Messages.ENTER_DEVELOPER_FIRSTNAME);
        String firstName = scanner.nextLine(); // FIXME first input from scanner do not work
        firstName = scanner.nextLine();
        System.out.println(Messages.ENTER_DEVELOPER_LASTNAME);
        String lastName = scanner.nextLine();
        List<Skill> skills = skillView.createSkill(scanner);
        Specialty specialty = specialtyView.createSpecialty(scanner);
        DeveloperController controller = new DeveloperController();
        Developer newDeveloper = new Developer(firstName, lastName, skills, specialty);
        System.out.println(controller.saveDeveloper(newDeveloper));
    }

    public void listAllDevelopers() {
        List<Developer> developers = controller.getAllDevelopers();
        developers.forEach(System.out::println);
    }
}
