package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.example.controller.SkillController;
import com.example.model.Skill;
import com.example.utils.IOUtils;
import com.example.utils.Messages;

public class SkillView {

    private static final SkillController controller = new SkillController();

    public List<Skill> createSkill(Scanner scanner) {
        List<Skill> result = new ArrayList<>();
        while (true) {
            if (controller.getAllSkills().isEmpty()) {
                System.out.println(Messages.ENTER_DEVELOPER_SKILL);
                addAndSaveNewSkills(scanner);
            } else {
                System.out.println("Find some skills:");
                controller.getAllSkills().forEach(System.out::println);
            }
            System.out.println("Add other ones or choose ?\n 1 add\n 2 choose\ntype a number");
            int inputFromUser = scanner.nextInt();
            if (inputFromUser == 1) {
                addAndSaveNewSkills(scanner);
            }
            System.out.println("Find some skills:");
            controller.skillService.printItemsWithIndex(controller.getAllSkills())
                    .forEach(System.out::println);
            System.out.println("Which one do you want to add?\nEnter numbers separated by spaces:");
            String listOfAllSkills = scanner.nextLine(); // FIXME add input validation
            listOfAllSkills = scanner.nextLine();
            if (IOUtils.isStringContainsOnlyNumbers(listOfAllSkills)) {
                result = controller.skillService.createListOfSkillFromStringOfId(listOfAllSkills);
            }
            System.out.println("You add this skill:");
            result.forEach(System.out::println);
            System.out.println("It's ok?\n 1 yes\n 2 no");
            inputFromUser = scanner.nextInt();
            if (inputFromUser == 1) {
                break;
            }
        }
        return result;
    }

    private static void addAndSaveNewSkills(Scanner scanner) {
        System.out.println(Messages.CREATE_NEW_SKILLS);
        while (true) {
            String skillName = scanner.nextLine();
            if (Objects.equals(skillName, "quit")) {
                break;
            }
            controller.saveSkill(skillName);
        }
    }
}
