package com.nnk.springboot.controllers;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;


@Controller
public class CurveController
{
    //=========================
    //=      Attributes       =
    //=========================
    private final CurvePointService curvePointService;

    //=========================
    //=     Constructors      =
    //=========================
    public CurveController(CurvePointService curvePointService)
    {
        this.curvePointService = curvePointService;
    }

    //=========================
    //=  Controller methods   =
    //=========================

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.getAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid)
    {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model)
    {
        if (!result.hasErrors())
        {
            curvePointService.save(curvePoint);
            model.addAttribute("curvePoints", curvePointService.getAll());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        CurvePoint curvePoint = curvePointService.getById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "curvePoint/update";
        }

        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.getAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model)
    {
        curvePointService.deleteById(id);
        model.addAttribute("curvePoints", curvePointService.getAll());
        return "redirect:/curvePoint/list";
    }
}