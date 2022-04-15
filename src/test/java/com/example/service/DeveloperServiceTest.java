package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.model.Developer;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeveloperServiceTest {

    private DeveloperRepository repository;
    private DeveloperService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(DeveloperRepository.class);
        service = new DeveloperService(repository);
    }

    @Test
    void givenDeveloper_whenInvokedGetDeveloperById_thanCompareByAllFieldsWithReturnDeveloper() {
        var expected = Developer.of(1, "FirstName", "LastName", "Specialty");
        when(repository.getById(anyInt())).thenReturn(expected);
        Developer actual = service.getById(anyInt());
        assertThat(actual.getId()).isNotNull().isEqualTo(expected.getId());
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getSpecialty().getId()).isNull();
        assertThat(actual.getSpecialty().getName()).isEqualTo(expected.getSpecialty().getName());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void givenListOfDevelopers_whenInvokedGetAllDeveloper_thanCompareByAllFieldsWithReturnListOfDevelopers () {
        List<Developer> expected = List.of(
                Developer.of(1, "FirstName1", "LastName1", "Specialty1"),
                Developer.of(2, "FirstName2", "LastName2", "Specialty2"),
                Developer.of(3, "FirstName3", "LastName3", "Specialty3"));
        when(repository.getAll()).thenReturn(expected);
        List<Developer> actual = service.getAll();
        assertThat(actual)
                .isNotEmpty()
                .hasSize(expected.size())
                .allSatisfy(developer -> {
                    assertThat(developer.getId()).isNotNull();
                    assertThat(developer).extracting(Developer::getStatus).isNotNull().isEqualTo(Status.ACTIVE);
                });
    }

    @Test
    void givenDeveloper_whenInvokedUpdate_thanCompareByAllFieldsWithReturnDeveloper() {
        var expected = Developer.of(1, "FirstName", "LastName", "Specialty");
        when(repository.update(any())).thenReturn(expected);
        Developer actual = service.update(any());
        assertThat(actual.getId()).isNotNull().isEqualTo(expected.getId());
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getSpecialty().getId()).isNull();
        assertThat(actual.getSpecialty().getName()).isEqualTo(expected.getSpecialty().getName());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void givenDeveloper_whenInvokedSave_thanCompareByAllFieldsWithReturnDeveloper() {
        var expected = Developer.of(1, "FirstName", "LastName", "Specialty");
        when(repository.save(any())).thenReturn(expected);
        Developer actual = service.save(any());
        assertThat(actual.getId()).isNotNull().isEqualTo(expected.getId());
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getSpecialty().getId()).isNull();
        assertThat(actual.getSpecialty().getName()).isEqualTo(expected.getSpecialty().getName());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void when_InvokedDeleteByIdOnServiceInstance_thanExpectInvokeDeleteByIdOnRepositoryInstance() {
        service.deleteById(anyInt());
        verify(repository).deleteById(anyInt());
    }
}
