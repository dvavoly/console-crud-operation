package com.example.view;

import com.example.controller.SpecialtyController;
import com.example.model.Specialty;
import com.example.utils.utils;

import java.util.Scanner;

public class SpecialtyView {

    private static final SpecialtyController controller = new SpecialtyController();

    public Specialty createSpecialty(Scanner scanner) {
        Specialty result = null;
        if (controller.getAllSpecialties().isEmpty()) {
            System.out.print("Input Specialty: ");
            result = controller.save(new Specialty(scanner.nextLine()));
        } else {
            controller.getAllSpecialtyWithId().forEach(System.out::println);
            System.out.print("The above list of available specialties, enter a number or type a new one to add it: ");
            String inputFromUser = scanner.nextLine(); //FIXME it does not work (((
//            inputFromUser = scanner.nextLine();
            if (utils.isStringContainsOnlyNumbers(inputFromUser)) {
                result = controller.getById(Integer.valueOf(inputFromUser));
            } else {
                result = controller.save(new Specialty(inputFromUser));
            }
        }
//        System.out.println(result);
        return result;
    }

}
