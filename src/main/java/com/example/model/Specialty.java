package com.example.model;

public class Specialty {
    private Integer id;
    private String name;

    public Specialty(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Specialty(String name) {
        this.id = null;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
