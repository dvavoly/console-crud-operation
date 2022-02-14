package com.example.utils;

public enum Messages {
    GREETINGS("""
            Hello,
            In this program, you can create or view a Developer with skills and specialty.
            Enter a number:
            1:Create a new Developer?
            2:List available Developers?
            """),
    BAD_INPUT("Bad input."),
    ENTER_DEVELOPER_FIRSTNAME("Enter developer First Name: "),
    ENTER_DEVELOPER_LASTNAME("Enter developer Last Name: "),
    ENTER_DEVELOPER_SKILL("Enter Developer Skill"),
    CREATE_NEW_SKILLS("We do not have any Skills.\nPlease, add a skill one by line, for exit input quit.");
    private final String value;

    Messages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
