package com.example.repository.gson;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.google.gson.Gson;

import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "src/main/resources/specialties.json";

    @Override
    public Specialty getById(Integer integer) {
        return null;
    }

    @Override
    public List<Specialty> getAll() {
        return null;
    }

    @Override
    public Specialty save(Specialty specialty) {
        return null;
    }

    @Override
    public Specialty update(Specialty specialty) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
