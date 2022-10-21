package com.nnk.springboot.service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class BidListServiceTest
{
    @InjectMocks
    BidListService bidListService;

    @Mock
    BidListRepository bidListRepository;

    @BeforeEach
    void init_mocks()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save()
    {
        bidListService.save(new BidList("accountTest", "typeTest", 99.99));
        verify(bidListRepository, times(1)).save(any());
    }

    @Test
    void getByIdFindExistingEntity()
    {
        when(bidListRepository.findById(99)).thenReturn(Optional.of(new BidList("accountTest", "typeTest", 99.99)));
        BidList bidList = bidListService.getById(99);
        assertEquals("accountTest", bidList.getAccount());
        assertEquals("typeTest", bidList.getType());
        assertEquals(99.99, bidList.getBidQuantity(), 0.01);
    }

    @Test
    void getByIdThrowsExceptionForNonExistingEntity()
    {
        assertThrows(EntityDoesNotExistException.class, () -> bidListService.getById(999));
    }


    @Test
    void getAll()
    {
        bidListService.getAll();
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        when(bidListRepository.findById(999)).thenReturn(Optional.of(new BidList("accountTest", "typeTest", 99.99)));
        bidListService.deleteById(999);
        verify(bidListRepository, times(1)).deleteById(999);
    }
}