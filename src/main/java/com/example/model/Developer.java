package com.example.model;

import java.util.List;

public class Developer {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private Specialty specialty;
    private Status status;

    public Developer(String firstName, String lastName, List<Skill> skills, Specialty specialty) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
        this.status = Status.ACTIVE;
    }

    public static Developer of() {
        return new Developer("", "", List.of(), new Specialty("Empty"));
    }

    public static Developer of(int id, String firstName, String lastName, String specialty_name) {
        var output = new Developer(firstName, lastName, List.of(), new Specialty(specialty_name));
        output.setId(id);
        return output;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", Skill: " + skills + ", Specialty: " + specialty;
    }
}
