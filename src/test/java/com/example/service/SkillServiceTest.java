package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.model.Skill;
import com.example.repository.SkillRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SkillServiceTest {

    SkillRepository repository;
    SkillService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(SkillRepository.class);
        service = new SkillService(repository);
    }

    @Test
    void givenSkill_whenInvokedGetSkillById_thanCompareByAllFieldsWithReturnSkill() {
        var skill = new Skill("TestSkill", 1);
        when(repository.getById(anyInt())).thenReturn(skill);
        Skill actual = service.getById(anyInt());
        assertEquals(skill.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void givenListOfSkills_whenInvokedGetAllSkill_thanCompareByAllFieldsWithReturnListOfSkill() {
        List<Skill> expected = List.of(
                new Skill("Java", 1),
                new Skill("SQL", 2),
                new Skill("JavaScript", 3));
        when(repository.getAll()).thenReturn(expected);
        List<Skill> actual = service.getAll();
        assertEquals(expected, actual);
        assertThat(actual).allSatisfy(skill -> {
            assertThat(skill.getId()).isNotNull();
        });
    }

    @Test
    void givenSkill_whenInvokedUpdate_thanCompareByAllFieldsWithReturnSkill() {
        var expected = new Skill("TestSkill", 1);
        when(repository.update(any())).thenReturn(expected);
        Skill actual = service.update(any());
        assertEquals(expected.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    void givenSkill_whenInvokedSave_thanCompareByAllFieldsWithReturnSkill() {
        var expected = new Skill("TestSkill", 1);
        when(repository.save(any())).thenReturn(expected);
        Skill actual = service.save(any());
        assertEquals(expected.getName(), actual.getName());
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    void when_InvokedDeleteByIdOnServiceInstance_thanExpectInvokeDeleteByIdOnRepositoryInstance() {
        service.deleteById(anyInt());
        verify(repository).deleteById(anyInt());
    }
}
