package com.example.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.example.modev.Skill;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/*
	public Skill save(Skill s) //id=null, name = TEST - completed
	public Skill update(Skill s)//
	public Skill getById(Integer id)..
	public List<Skill> getAll()...
	public void deleteById(Integer id)
*/

public class SkillRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/skills.json";

    public Skill save(Skill s) {
        List<Skill> skills = readSkillFromFile();
        s = setUniqueId(skills, s);
        skills.add(s);
        writeSkillToFile(skills);
        return s;
    }

    public List<Skill> getAll() {
        return readSkillFromFile();
    }

    private static List<Skill> readSkillFromFile() {
        List<Skill> outList = null;
        if (Files.exists(Path.of(fileName))) {
            try (FileReader reader = new FileReader(fileName)) {
                outList = gson.fromJson(reader, new TypeToken<List<Skill>>() {
                }.getType());
            } catch (JsonIOException | JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ArrayList<Skill>();
        }
        return outList;
    }

    private static void writeSkillToFile(List<Skill> skills) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(skills, writer);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    private Skill setUniqueId(List<Skill> skills, Skill s) {

        if (skills.isEmpty()) {
            s.setId(1);
            return s;
        }

        // get a last element of an array
        Skill lastElement = skills.get(skills.size() - 1);
        // generate new is by add plus one to id of the last element
        int newId = lastElement.getId() + 1;
        s.setId(newId);
        return s;
    }
}
