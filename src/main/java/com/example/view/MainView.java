package com.example.view;

import com.example.utils.Messages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    DeveloperView developerView = new DeveloperView();

    public void showMainMenu() {
        boolean showMenu = true;
        System.out.println(Messages.GREETINGS);
        try (var scanner = new Scanner(System.in)) {
            while (showMenu) {
                System.out.println(Messages.MAINMENU);
                switch (scanner.nextInt()) {
                    case 0 -> showMenu = false; // exit from loop and program
                    case 1 -> developerView.createDeveloper(scanner);
                    case 2 -> developerView.listAllDevelopers();
                    case 3 -> developerView.updateDeveloper(scanner);
                    case 4 -> developerView.deleteDeveloper(scanner);
                    default -> System.out.println(Messages.BAD_INPUT);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(Messages.BAD_INPUT);
        }
    }
}
