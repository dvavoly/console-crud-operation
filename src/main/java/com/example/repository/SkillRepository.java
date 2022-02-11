package com.example.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.example.modev.Skill;
import com.example.modev.Status;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class SkillRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/skills.json";

    public Skill save(Skill s) {
        List<Skill> skills = readSkillFromFile();
        s.setId(generateNewMaxId(skills));
        skills.add(s);
        writeSkillToFile(skills);
        return s;
    }

    public Skill update(Skill s) {
        List<Skill> skills = readSkillFromFile();
        for (Skill item : skills) {
            if (Objects.equals(item.getId(), s.getId())) {
                item.setName(s.getName());
                return item;
            }
        }
        writeSkillToFile(skills);
        return new Skill("", -1);
    }

    /**
     * @param id - An id of the searching element.
     * @return a new object with id = -1 and an empty string in a field name
     */
    public Skill getById(Integer id) {
        List<Skill> skills = readSkillFromFile();
        for (Skill item : skills) {
            if (Objects.equals(item.getId(), id)) {
                return item;
            }
        }
        return new Skill("", -1);
    }

    /**
     * @return - All elements stored in a file
     */
    public List<Skill> getAll() {
        return readSkillFromFile();
    }

    public void deleteById(Integer id) {
        List<Skill> skills = readSkillFromFile();
        for (Skill item : skills) {
            if (Objects.equals(item.getId(), id)) {
                item.setStatus(Status.DELETED);
                break;
            }
        }
        writeSkillToFile(skills);
    }

    private Integer generateNewMaxId(List<Skill> skills) {
        Skill skillWithMaxId = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
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
            return new ArrayList<>();
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
}
