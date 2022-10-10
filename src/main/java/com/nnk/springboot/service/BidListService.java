package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.domain.BidList;


@Service
public class BidListService
{
    //=========================
    //=      Attributes       =
    //=========================
    private final BidListRepository bidListRepository;

    //=========================
    //=     Constructors      =
    //=========================

    public BidListService(BidListRepository bidListRepository)
    {
        this.bidListRepository = bidListRepository;
    }

    //=========================
    //=    Service methods    =
    //=========================

    public BidList save(BidList bidList)
    {
        return bidListRepository.save(bidList);
    }

    public BidList getById(int id)
    {
        return bidListRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("Bid List Id: " + id + " does not exist."));
    }

    public List<BidList> getAll()
    {
        return bidListRepository.findAll();
    }

    public void deleteById(int id)
    {
        bidListRepository.deleteById(id);
    }
}