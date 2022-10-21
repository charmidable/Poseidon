package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.domain.Rating;




@Service
public class RatingService
{
    //=========================
    //=      Attributes       =
    //=========================
    private final RatingRepository ratingRepository;

    //=========================
    //=     Constructors      =
    //=========================

    public RatingService(RatingRepository ratingRepository)
    {
        this.ratingRepository = ratingRepository;
    }

    //=========================
    //=    Service methods    =
    //=========================

    public Rating save(Rating rating)
    {
        return ratingRepository.save(rating);
    }

    public Rating getById(int id)
    {
        return ratingRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("Rating Id: " + id + " does not exist."));
    }

    public List<Rating> getAll()
    {
        return ratingRepository.findAll();
    }

    public void deleteById(int id)
    {
        ratingRepository.deleteById(id);
    }
}
