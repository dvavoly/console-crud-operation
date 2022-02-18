package com.example.repository.gson;

import com.example.model.Developer;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;
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

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/developers.json";

    @Override
    public Developer save(Developer developer) {
        List<Developer> developers = readFromFile(fileName);
        developer.setId(generateNewMaxId(developers));
        developers.add(developer);
        writeToFile(developers, fileName);
        return developer;
    }

    @Override
    public Developer getById(Integer id) {
        return readFromFile(fileName).stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return readFromFile(fileName);
    }

    @Override
    public Developer update(Developer dev) {
        List<Developer> developers = readFromFile(fileName);
        for (int i = 0; i < developers.size() - 1; i++) {
            if (Objects.equals(developers.get(i), dev.getId())) {
                developers.set(i, dev);
                break;
            }
        }
        writeToFile(developers, fileName);
        return dev;
    }

    @Override
    public void deleteById(Integer id) {
        List<Developer> developers = readFromFile(fileName);
        for (Developer item : developers) {
            if (item.getId().equals(id)) {
                item.setStatus(Status.DELETED);
            }
        }
        writeToFile(developers, fileName);
    }

    public static Integer generateNewMaxId(List<Developer> list) {
        Developer developerWithMaxId = list.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(developerWithMaxId) ? developerWithMaxId.getId() + 1 : 1;
    }

    private static List<Developer> readFromFile(String fileName) {
        List<Developer> outList = null;
        if (Files.exists(Path.of(fileName))) {
            try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
                outList = gson.fromJson(reader, new TypeToken<List<Developer>>() {
                }.getType());
            } catch (JsonIOException | JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ArrayList<>();
        }
        return outList;
    }

    private static void writeToFile(List<Developer> developers, String fileName) {
        if (!Files.exists(Path.of("src/main/resources/"))) {
            try {
                Files.createDirectories(Path.of("src/main/resources/"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            gson.toJson(developers, writer);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }
}
