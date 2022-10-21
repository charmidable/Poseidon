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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

class RatingServiceTest
{
    @InjectMocks
    RatingService ratingService;

    @Mock
    RatingRepository ratingRepository;

    @BeforeEach
    void init_mocks()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save()
    {
        ratingService.save(new Rating("moodysRating", "sandPRating", "fitchRating", 1));
        verify(ratingRepository, times(1)).save(any());
    }

    @Test
    void getByIdFindExistingEntity()
    {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(new Rating("moodysRating","sandPRating","fitchRating",1)));
        Rating rating = ratingService.getById(1);
        assertEquals("moodysRating", rating.getMoodysRating());
        assertEquals("sandPRating", rating.getSandPRating());
        assertEquals("fitchRating", rating.getFitchRating());
        assertEquals(1, rating.getOrderNumber(), 0.1);
    }

    @Test
    void getByIdThrowsExceptionForNonExistingEntity()
    {
        assertThrows(EntityDoesNotExistException.class, () -> ratingService.getById(999));
    }

    @Test
    void getAll()
    {
        ratingService.getAll();
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        when(ratingRepository.findById(999)).thenReturn(Optional.of(new Rating("moodysRating","sandPRating","fitchRating",1)));
        ratingService.deleteById(999);
        verify(ratingRepository, times(1)).deleteById(999);
    }
}