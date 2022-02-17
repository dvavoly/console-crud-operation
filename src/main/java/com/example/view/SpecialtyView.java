package com.example.view;

import com.example.controller.SpecialtyController;
import com.example.model.Specialty;

import java.util.Scanner;

import static com.example.utils.utils.isNumber;

public class SpecialtyView {

    private static final SpecialtyController controller = new SpecialtyController();

    public Specialty createSpecialty(Scanner scanner) {
        Specialty outSpecialty = null;
        if (controller.getAllSpecialties().isEmpty()) {
            System.out.print("Input Specialty: ");
            Specialty specialty = new Specialty(scanner.nextLine());
            controller.save(specialty);
            outSpecialty = controller.getAllSpecialties().get(0);
        } else {
            for (String item : controller.printItemsWithIndex(controller.getAllSpecialties())) {
                System.out.println(item);
            }
            System.out.print("The above list of available specialties, enter a number or type a new one to add it: ");
            String inputFromUser = scanner.nextLine();
            if (isNumber(inputFromUser)) {
                outSpecialty = controller.getById(Integer.valueOf(inputFromUser));
            } else {
                outSpecialty = controller.save(new Specialty(inputFromUser));
            }
        }
        return outSpecialty;
    }
}
