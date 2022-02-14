package com.example.view;

import com.example.controller.SkillController;
import com.example.utils.Messages;

import java.util.Objects;
import java.util.Scanner;

public class SkillView {

    private final SkillController skillController = new SkillController();

    public String createSkill(Scanner scanner) {
        if (skillController.isEmptySkillsList()) {
            System.out.println(Messages.ENTER_DEVELOPER_SKILL);
            System.out.println(Messages.CREATE_NEW_SKILLS);
            while (true) {
                String skill = scanner.nextLine();
                if (Objects.equals(skill, "quit")) {
                    break;
                }
                skillController.saveSkill(skill);
            }
        }
        skillController.printSkillsWithIndex().forEach(System.out::println);
        System.out.println("Which one do you want to add?\nEnter numbers separated by spaces:");
        return scanner.nextLine(); //FIXME add input validation
    }
}
