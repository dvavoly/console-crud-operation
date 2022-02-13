package com.example.view;

import com.example.utils.Messages;

import java.util.Scanner;

public class DeveloperView {

    SkillView skillView;

    public DeveloperView() {
        skillView = new SkillView();
    }

    public void createDeveloper(Scanner scanner) {
        System.out.print(Messages.ENTER_DEVELOPER_FIRSTNAME);
        String firstName = scanner.nextLine();
        System.out.println(Messages.ENTER_DEVELOPER_LASTNAME);
        String lastName = scanner.nextLine();
        skillView.createSkill(scanner);
    }
}
