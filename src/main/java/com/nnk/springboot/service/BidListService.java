package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

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

    public boolean checkIfIdExists(int id)
    {
        return bidListRepository.existsById(id);
    }

    public List<BidList> getAll()
    {
        return bidListRepository.findAll();
    }

    public void deleteById(int id)
    {
        bidListRepository.deleteById(id);
    }

    public Boolean updateBidList(int id, BidList bidList) {

        Optional<BidList> listBidList = bidListRepository.findById(id);
        if (listBidList.isPresent()) {
            BidList newBidList = listBidList.get();
            newBidList.setAccount(bidList.getAccount());
            newBidList.setType(bidList.getType());
            newBidList.setBidQuantity(bidList.getBidQuantity());
            bidListRepository.save(newBidList);
//            log.info("BidList with id " + id + " is updated as " + newBidList);
            return true;
        }
//        log.info("Failed to update BidList with id " + id + " as" + bidList);
        return false;
    }
}
