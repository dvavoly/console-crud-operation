package com.example.controller;

import com.example.view.DeveloperView;

import java.util.Scanner;

public class DeveloperController {

    private Scanner scanner;
    private DeveloperView view;

    public DeveloperController(Scanner scanner) {
        this.scanner = scanner;
        view = new DeveloperView();
    }

    public void start() {
        view.startUserInterface();
        switch (scanner.nextInt()) {
            // create a new developer
            case 1 -> System.out.println("Create a new developer");
            // create a new skill
            case 2 -> System.out.println("Create a new skill");
            // create a new specialty
            case 3 -> System.out.println("Create a new specialty");
            default -> System.out.println("Bad input");
        }
    }
}
