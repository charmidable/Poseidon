package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController
{
    //=========================
    //=      Attributes       =
    //=========================
    private final RatingService ratingService;

    //=========================
    //=     Constructors      =
    //=========================
    public RatingController(RatingService ratingService)
    {
        this.ratingService = ratingService;
    }


    //=========================
    //=  Controller methods   =
    //=========================

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating)
    {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model)
    {
        if (!result.hasErrors())
        {
            ratingService.save(rating);
            model.addAttribute("ratings", ratingService.getAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        Rating curvePoint = ratingService.getById(id);
        model.addAttribute("rating", curvePoint);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "rating/update";
        }

        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("ratings", ratingService.getAll());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model)
    {
        ratingService.deleteById(id);
        model.addAttribute("ratings", ratingService.getAll());
        return "redirect:/rating/list";
    }
}
