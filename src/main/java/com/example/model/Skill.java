package com.example.model;

public class Skill implements Entity {
    private Integer id;
    private String name;

    public Skill(String name) {
        this.name = name;
        id = null;
    }

    public Skill(String name, Integer id) {
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "Skill: " + name + ", id: " + id;
    }
}