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
        System.out.print(Messages.ENTER_DEVELOPER_FIRSTNAME);
        String firstName = scanner.nextLine(); // FIXME first input from scanner do not work
        firstName = scanner.nextLine();
        System.out.print(Messages.ENTER_DEVELOPER_LASTNAME);
        String lastName = scanner.nextLine();
        Specialty specialty = specialtyView.createSpecialty(scanner);
        List<Skill> skills = skillView.createSkill(scanner);
        Developer newDeveloper = new Developer(firstName, lastName, skills, specialty);
        System.out.println(controller.saveDeveloper(newDeveloper));
    }

    public void listAllDevelopers() {
        List<Developer> developers = controller.getAllDevelopers();
        if (developers.isEmpty()) {
            System.out.println(Messages.LIST_OF_DEVELOPERS_IS_EMPTY);
        } else {
            developers.forEach(System.out::println);
        }
    }

    public void updateDeveloper(Scanner scanner) {
        List<Developer> developers = controller.getAllDevelopers();
        if (developers.isEmpty()) {
            System.out.println(Messages.LIST_OF_DEVELOPERS_IS_EMPTY);
        } else {
            developers.forEach(d -> System.out.println(d.getId() + ":" + d));
            System.out.println("Which developer update?");
            Developer developer = controller.getDeveloper(scanner.nextInt());
            System.out.println(developer);
            System.out.println("You want update:\n1:First and Last Name?\n2:Skills?\n3:Specialty\n0:Do nothing");

            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.print("Input First name: ");
                    scanner.nextLine(); // FIXME first input from scanner do not work
                    String firstName = scanner.nextLine();
                    System.out.print("Input Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println(controller.developerService.updateFirstAndLastName(developer, firstName, lastName, controller));
                }
                case 2 -> {
                    System.out.println("Update Skills");
                    scanner.nextLine();
                    List<Skill> skills = skillView.createSkill(scanner);
                    controller.developerService.updateSkills(developer, skills, controller);
                }
                case 3 -> {
                    System.out.println("Update Specialty");
                    scanner.nextLine();
                    Specialty specialty = specialtyView.createSpecialty(scanner);
                    controller.developerService.updateSpecialty(developer, specialty, controller);
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println(Messages.BAD_INPUT);
            }
        }
        System.out.println("Developer successfully updated");
    }

    public void deleteDeveloper(Scanner scanner) {
        List<Developer> developers = controller.getAllDevelopers();
        if (developers.isEmpty()) {
            System.out.println(Messages.LIST_OF_DEVELOPERS_IS_EMPTY);
        } else {
            developers.forEach(d -> System.out.println(d.getId() + ":" + d));
            System.out.println("Which developer to remove?");
            controller.removeDeveloper(scanner.nextInt());
            System.out.println("Developer successfully removed");
        }
    }
}
