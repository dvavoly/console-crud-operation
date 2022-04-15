package com.example.utils;

public class IOUtils {

    public static boolean isStringContainsOnlyNumbers(String inputFromUser) {
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