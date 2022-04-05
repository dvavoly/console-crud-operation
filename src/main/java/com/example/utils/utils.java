package com.example.utils;

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
import java.util.stream.Stream;

import com.example.model.Entity;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class utils {

    private final static Gson gson = new Gson();

    public static <T> List<T> readFromFile(String fileName) {
        List<T> outList = null;
        if (Files.exists(Path.of(fileName))) {
            try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
                outList = gson.fromJson(reader, new TypeToken<List<T>>() {
                }.getType());
            } catch (JsonIOException | JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return new ArrayList<>();
        }
        return outList;
    }

    public static <T> void writeToFile(List<T> skills, String fileName) {
        if (!Files.exists(Path.of("src/main/resources/"))) {
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

    public static <T extends Entity> Integer generateNewMaxId(List<T> list) {
        T skillWithMaxId = list.stream().max(Comparator.comparing(T::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
    }

    public static <T extends Entity> List<String> printItemsWithIndex(List<T> items) {
        return items.stream().map(e -> e.getId() + ":" + e.getName()).toList();
    }

    public static boolean isNumber(String str) {
        if (Objects.isNull(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isStringContainsOnlyNumbers(String str) {
        if (Objects.isNull(str)) {
            return false;
        }
        return Stream.of(str.split("\\s+")).allMatch(utils::isNumber);
    }
}
