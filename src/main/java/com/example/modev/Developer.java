package com.example.modev;

import java.util.List;

public class Developer {
    private Integer id;
    private String firstName;
    private String secondName;
    private List<Skill> skills;
    private Specialty specialty;

    public Developer(Integer id, String firstName, String secondName, List<Skill> skills, Specialty specialty) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.skills = skills;
        this.specialty = specialty;
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

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
