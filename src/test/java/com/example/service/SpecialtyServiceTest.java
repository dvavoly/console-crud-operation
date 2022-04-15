package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialtyServiceTest {

    private SpecialtyService service;
    private SpecialtyRepository repository;

    @BeforeEach
    void initService() {
        repository = mock(SpecialtyRepository.class);
        service = new SpecialtyService(repository);
    }

    @Test
    void givenSpecialty_whenInvokedGetSpecialtyById_thanCompareByAllFieldsWithReturnSpecialty() {
        var specialty = new Specialty(1, "TestSpecialty");
        when(repository.getById(any(Integer.class))).thenReturn(specialty);
        Specialty actual = service.getById(10);
        assertEquals(specialty.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void givenListOfSpecialty_whenInvokedGetAllSpecialty_thanCompareByAllFieldsWithReturnListOfSpecialty() {
        List<Specialty> expected = List.of(
                new Specialty(1, "Java Developer"),
                new Specialty(2, "Database Administrator"),
                new Specialty(3, "Web Developer"));
        when(repository.getAll()).thenReturn(expected);
        List<Specialty> actual = service.getAll();
        assertThat(actual)
                .isEqualTo(expected)
                .allSatisfy(specialty -> {
                    assertThat(specialty.getId()).isNotNull();
                });
    }

    @Test
    void givenSpecialty_whenInvokedUpdate_thanCompareByAllFieldsWithReturnSpecialty() {
        var specialty = new Specialty(1, "TestSpecialty");
        when(service.update(specialty)).thenReturn(specialty);
        Specialty actual = service.update(specialty);
        assertEquals(specialty.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void givenSpecialty_whenInvokedSave_thanCompareByAllFieldsWithReturnSpecialty() {
        var specialty = new Specialty(1, "TestSpecialty");
        when(repository.save(specialty)).thenReturn(specialty);
        Specialty actual = service.save(specialty);
        assertEquals(specialty.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void when_InvokedDeleteByIdOnServiceInstance_thanExpectInvokeDeleteByIdOnRepositoryInstance() {
        service.deleteById(anyInt());
        verify(repository).deleteById(anyInt());
    }
}