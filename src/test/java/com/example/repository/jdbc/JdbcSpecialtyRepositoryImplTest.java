package com.example.repository.jdbc;

import com.example.model.Specialty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class JdbcSpecialtyRepositoryImplTest {

    JdbcSpecialtyRepositoryImpl repositoryMock = Mockito.mock(JdbcSpecialtyRepositoryImpl.class);

    @Test
    void getAll() {
        List<Specialty> data = new ArrayList<>();
        data.add(new Specialty("Empty"));
        Mockito.when(repositoryMock.getAll()).thenReturn(data);
    }
}
