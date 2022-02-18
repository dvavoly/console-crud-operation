package com.example.utils;

public enum Messages {
    GREETINGS("Hello,\nIn this program, you can create or view a Developer with skills and specialty."),
    MAINMENU("""
            Choose an option:
            1:Create a new Developer?
            2:Display available Developers?
            3:Delete/Update a developer?
            0:Exit.
            Select an option:"""),
    BAD_INPUT("Bad input."),
    ENTER_DEVELOPER_FIRSTNAME("Enter developer First Name: "),
    ENTER_DEVELOPER_LASTNAME("Enter developer Last Name: "),
    ENTER_DEVELOPER_SKILL("Enter Developer Skill"),
    CREATE_NEW_SKILLS("We do not have any Skills.\nPlease, add a skill one by line, for exit input quit."),
    LIST_OF_DEVELOPERS_IS_EMPTY("The list of developers is empty. Please choose the option in the main menu to create a new developer.");
    private final String value;

    Messages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
