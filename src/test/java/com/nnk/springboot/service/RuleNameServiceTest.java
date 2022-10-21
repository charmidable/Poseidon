package com.nnk.springboot.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;


class RuleNameServiceTest
{
    @InjectMocks
    RuleNameService ruleNameService;

    @Mock
    RuleNameRepository ruleNameRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save()
    {
        ruleNameService.save(new RuleName());
        verify(ruleNameRepository, times(1)).save(any());
    }

    @Test
    void getByIdFindExistingEntity()
    {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart")));
        RuleName ruleName = ruleNameService.getById(1);
        assertEquals("name", ruleName.getName());
        assertEquals("description", ruleName.getDescription());
        assertEquals("json", ruleName.getJson());
        assertEquals("template", ruleName.getTemplate());
        assertEquals("sqlStr", ruleName.getSqlStr());
        assertEquals("sqlPart", ruleName.getSqlPart());
    }

    @Test
    void getByIdThrowsExceptionForNonExistingEntity()
    {
        assertThrows(EntityDoesNotExistException.class, () -> ruleNameService.getById(999));
    }

    @Test
    void getAll()
    {
        ruleNameService.getAll();
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart")));
        ruleNameService.deleteById(1);
        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}