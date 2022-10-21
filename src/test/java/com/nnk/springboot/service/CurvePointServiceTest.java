package com.nnk.springboot.service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurvePointServiceTest
{
    @InjectMocks
    CurvePointService curvePointService;

    @Mock
    CurvePointRepository curvePointRepository;

    @BeforeEach
    void init_mocks()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save()
    {
        curvePointService.save(new CurvePoint(1, 1.1,  1.1));
        verify(curvePointRepository, times(1)).save(any());
    }

    @Test
    void getByIdFindExistingEntity()
    {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(new CurvePoint(1, 1.1,  1.1)));
        CurvePoint curvePoint = curvePointService.getById(1);
        assertEquals(1, curvePoint.getCurveId(),0.01);
        assertEquals(1.1, curvePoint.getTerm(), 0.01);
        assertEquals(1.1, curvePoint.getValue(), 0.01);
    }

    @Test
    void getByIdThrowsExceptionForNonExistingEntity()
    {
        assertThrows(EntityDoesNotExistException.class, () -> curvePointService.getById(999));
    }

    @Test
    void getAll()
    {
        curvePointService.getAll();
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        when(curvePointRepository.findById(999)).thenReturn(Optional.of(new CurvePoint(1, 1.1,  1.1)));
        curvePointService.deleteById(999);
        verify(curvePointRepository, times(1)).deleteById(999);
    }
}