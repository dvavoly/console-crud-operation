package com.example.view;

import com.example.utils.Messages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    DeveloperView developer = new DeveloperView();

    public MainView() {
    }

    public void showMainMenu() {
        System.out.println(Messages.GREETINGS);
        try (var scanner = new Scanner(System.in)) {
            switch (scanner.nextInt()) {
                case 1 -> developer.createDeveloper(scanner);
                case 2 -> System.out.println("2");
                default -> System.out.println(Messages.BAD_INPUT);
            }
        } catch (InputMismatchException e) {
            System.out.println(Messages.BAD_INPUT);
        }
    }
}
