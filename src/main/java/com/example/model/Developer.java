package com.example.model;

import java.util.List;

public class Developer {

    private Integer id;
    private List<Skill> skills;
    private Specialty specialty;
    private Status status;
    private String firstName;
    private String secondName;

    public Developer(String firstName, String lastName, List<Skill> skills, Specialty specialty) {
        this.id = null;
        this.firstName = firstName;
        this.secondName = lastName;
        this.skills = skills;
        this.specialty = specialty;
        this.status = Status.ACTIVE;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
        return firstName + " " + secondName + ", Skill: " + skills + ", Specialty: " + specialty;
    }
}
