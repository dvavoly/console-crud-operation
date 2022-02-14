package com.example.view;

import com.example.utils.Messages;

import java.util.Scanner;

public class DeveloperView {

    private final SkillView skillView = new SkillView();
    private final SpecialtyView specialtyView = new SpecialtyView();

    public void createDeveloper(Scanner scanner) {
        System.out.println(Messages.ENTER_DEVELOPER_FIRSTNAME);
        String firstName = scanner.nextLine(); // FIXME don't work first input from scanner
        firstName = scanner.nextLine();
        System.out.println(Messages.ENTER_DEVELOPER_LASTNAME);
        String lastName = scanner.nextLine();
        String stringContainsSkillsId = skillView.createSkill(scanner);
        String specialty = specialtyView.createSpecialty();

    }
}
