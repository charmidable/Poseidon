package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TradeController
{

    //=========================
    //=      Attributes       =
    //=========================
    private final TradeService tradeService;

    //=========================
    //=     Constructors      =
    //=========================
    public TradeController(TradeService tradeService)
    {
        this.tradeService = tradeService;
    }

    //=========================
    //=  Controller methods   =
    //=========================

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> tradeList = tradeService.getAll();
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid)
    {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model)
    {
        if (!result.hasErrors())
        {
            tradeService.save(trade);
            model.addAttribute("tradeList", tradeService.getAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        Trade trade = tradeService.getById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "trade/update";
        }
        Trade trade1 = tradeService.save(trade);
        if (trade1 != null)
        {
            model.addAttribute("tradeList", tradeService.getAll());
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model)
    {
        Trade trade = tradeService.getById(id);
        if (trade != null)
        {
            tradeService.deleteById(id);
            model.addAttribute("tradeList", tradeService.getAll());
            return "redirect:/trade/list";
        }
        else
        {
            throw new IllegalArgumentException("Invalid trade id " + id);
        }
    }
}