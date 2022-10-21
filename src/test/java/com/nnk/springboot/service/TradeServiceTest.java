package com.nnk.springboot.service;

import java.util.Optional;
import com.nnk.springboot.domain.Trade;
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
import com.nnk.springboot.repositories.TradeRepository;

class TradeServiceTest
{
    @InjectMocks
    TradeService tradeService;

    @Mock
    TradeRepository tradeRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save()
    {
        tradeService.save(new Trade("account", "type"));
        verify(tradeRepository, times(1)).save(any());
    }

    @Test
    void getByIdFindExistingEntity()
    {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(new Trade("account", "type")));
        Trade trade = tradeService.getById(1);
        assertEquals("account", trade.getAccount());
        assertEquals("type", trade.getDealType());
    }

    @Test
    void getByIdThrowsExceptionForNonExistingEntity()
    {
        assertThrows(EntityDoesNotExistException.class, () -> tradeService.getById(999));
    }

    @Test
    void getAll()
    {
        tradeService.getAll();
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(new Trade("account", "type")));
        tradeService.deleteById(1);
        verify(tradeRepository, times(1)).deleteById(1);
    }
}