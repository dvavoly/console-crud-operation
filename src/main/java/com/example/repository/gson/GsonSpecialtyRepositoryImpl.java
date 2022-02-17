package com.example.repository.gson;

import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.utils.utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/specialties.json";

    @Override
    public Specialty save(Specialty s) {
        List<Specialty> specialties = readFromFile(fileName);
        s.setId(generateNewMaxId(specialties));
        specialties.add(s);
        writeToFile(specialties, fileName);
        return s;
    }

    @Override
    public Specialty getById(Integer id) {
        return readFromFile(fileName).stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return readFromFile(fileName);
    }

    @Override
    public Specialty update(Specialty s) {
        List<Specialty> specialties = readFromFile(fileName);
        for (Specialty item : specialties) {
            if (Objects.equals(item.getId(), s.getId())) {
                item.setName(s.getName());
            }
        }
        return s;
    }

    @Override
    public void deleteById(Integer id) {
        List<Specialty> specialties = readFromFile(fileName);
        specialties.removeIf(s -> s.getId().equals(id));
        writeToFile(specialties, fileName);
    }

    public static Integer generateNewMaxId(List<Specialty> list) {
        Specialty skillWithMaxId = list.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
    }

    private static List<Specialty> readFromFile(String fileName) {
        List<Specialty> outList = null;
        if (Files.exists(Path.of(fileName))) {
            try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
                outList = gson.fromJson(reader, new TypeToken<List<Specialty>>() {
                }.getType());
            } catch (JsonIOException | JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ArrayList<>();
        }
        return outList;
    }

    private static void writeToFile(List<Specialty> specialties, String fileName) {
        if (!Files.exists(Path.of("src/main/resources/"))) {
            try {
                Files.createDirectories(Path.of("src/main/resources/"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            gson.toJson(specialties, writer);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }
}
