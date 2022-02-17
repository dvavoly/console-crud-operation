package com.example.view;

import com.example.controller.SkillController;
import com.example.model.Skill;
import com.example.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SkillView {

    private final SkillController controller = new SkillController();

    public List<Skill> createSkill(Scanner scanner) {
        List<Skill> outList = new ArrayList<>();
        if (controller.getAllSkills().isEmpty()) {
            System.out.println(Messages.ENTER_DEVELOPER_SKILL);
            System.out.println(Messages.CREATE_NEW_SKILLS);
            while (true) {
                String skill = scanner.nextLine();
                if (Objects.equals(skill, "quit")) {
                    break;
                }
                controller.saveSkill(skill);
            }
        }
        controller.printItemsWithIndex(controller.getAllSkills())
                .forEach(System.out::println);
        System.out.println("Which one do you want to add?\nEnter numbers separated by spaces:");
        String listOfAllSkills = scanner.nextLine(); //FIXME add input validation
        return SkillController.createListOfSkillFromStringOfId(listOfAllSkills);
    }

}
