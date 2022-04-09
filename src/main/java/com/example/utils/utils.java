package com.example.utils;

public class utils {

    public static boolean isStringContainsOnlyNumbers(String inputFromUser) {
//        for (char ch : inputFromUser.toCharArray()) {
//            if (!Character.isDigit(ch)) {
//                return false;
//            }
//        }
//        return true;
        String[] s = inputFromUser.split(" ");
        for (String item : s) {
            for (char ch : item.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
        }
        return true;
    }
}