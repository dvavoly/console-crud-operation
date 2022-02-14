package com.example.repository.gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.example.model.Skill;
import com.example.repository.GenericRepository;
import com.example.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/skills.json";

    @Override
    public Skill save(Skill s) {
        List<Skill> skills = readSkillFromFile();
        s.setId(generateNewMaxId(skills));
        skills.add(s);
        writeSkillToFile(skills);
        return s;
    }

    @Override
    public Skill update(Skill s) {
        List<Skill> skills = readSkillFromFile();
        for (Skill item : skills) {
            if (Objects.equals(item.getId(), s.getId())) {
                item.setName(s.getName());
                return item;
            }
        }
        writeSkillToFile(skills);
        return null;
    }

    @Override
    public List<Skill> getAll() {
        return readSkillFromFile();
    }

    @Override
    public Skill getById(Integer id) {
        return readSkillFromFile().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> skills = readSkillFromFile();
        skills.removeIf(s -> s.getId().equals(id));
        writeSkillToFile(skills);
    }

    private Integer generateNewMaxId(List<Skill> skills) {
        Skill skillWithMaxId = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
    }

    private static List<Skill> readSkillFromFile() {
        List<Skill> outList = null;
        if (Files.exists(Path.of(fileName))) {
            try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
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
        if (!Files.exists(Path.of("src/main/resources/"))){
            try {
                Files.createDirectories(Path.of("src/main/resources/"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            gson.toJson(skills, writer);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }
}
