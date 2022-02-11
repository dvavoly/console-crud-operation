package com.example.modev;

/**
 * Class Skill contains information about Developer skills
 */
public class Skill {
    private Integer id;
    private String name;
    private Status status;

    public Skill(String name) {
        this.name = name;
        id = null;
        status = Status.ACTIVE;
    }

    public Skill(String name, Integer id) {
        this.name = name;
        this.id = id;
        status = Status.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Skill: " + name + ", id: " + id;
    }
}
