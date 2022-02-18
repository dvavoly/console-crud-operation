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
        List<Skill> skills = skillView.createSkill(scanner);
        Specialty specialty = specialtyView.createSpecialty(scanner);
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

    public void updateOrDeleteDevelopers(Scanner scanner) {
        List<Developer> developers = controller.getAllDevelopers();
        if (developers.isEmpty()) {
            System.out.println(Messages.LIST_OF_DEVELOPERS_IS_EMPTY);
        } else {
            System.out.println("Choose an option:\n1:Delete\n2:Update");
            int inputFromUser = scanner.nextInt();

            developers.forEach(d -> System.out.println(d.getId() + ":" + d));

            if (inputFromUser == 1) {
                System.out.println("Which developer to remove?");
                controller.removeDeveloper(scanner.nextInt());
                System.out.println("Developer successfully removed");
            }

            if (inputFromUser == 2) {
                System.out.println("Which developer update?");
                System.out.println("Developer successfully updated");
            }
        }
    }
}
