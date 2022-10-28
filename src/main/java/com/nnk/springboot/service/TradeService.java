package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.domain.Trade;


@Service
public class TradeService
{
    //=========================
    //=      Attributes       =
    //=========================
    private final TradeRepository tradeRepository;

    //=========================
    //=     Constructors      =
    //=========================

    public TradeService(TradeRepository tradeRepository)
    {
        this.tradeRepository = tradeRepository;
    }



    //=========================
    //=    Service methods    =
    //=========================

    public Trade save(Trade trade)
    {
        return tradeRepository.save(trade);
    }

    public Trade getById(Integer id)
    {
        return tradeRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("Trade Id: " + id + " does not exist."));
    }

    public List<Trade> getAll()
    {
        return tradeRepository.findAll();
    }

    public void deleteById(Integer id)
    {
        tradeRepository.deleteById(id);
    }
}
