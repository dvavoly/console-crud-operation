package com.example.repository.gson;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.utils.utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/specialties.json";

    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> specialties = readFromFile(fileName);
        specialty.setId(utils.<Specialty>generateNewMaxId(specialties));
        specialties.add(specialty);
        utils.writeToFile(specialties, fileName);
        return specialty;
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
    public Specialty update(Specialty specialty) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        List<Specialty> specialties = readFromFile(fileName);
        specialties.removeIf(s -> s.getId().equals(id));
        utils.writeToFile(specialties, fileName);
    }

    public static List<Specialty> readFromFile(String fileName) {
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

    public static void main(String[] args) {
        Specialty specialty = new Specialty("Scala");
        GsonSpecialtyRepositoryImpl specialtyRepository = new GsonSpecialtyRepositoryImpl();
        specialtyRepository.save(specialty);
        System.out.println(specialtyRepository.getAll());
    }
}
