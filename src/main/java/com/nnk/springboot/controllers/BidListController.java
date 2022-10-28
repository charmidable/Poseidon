package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;


@Controller
public class BidListController
{
    //=========================
    //=      Attributes       =
    //=========================
    private final BidListService bidListService;

    //=========================
    //=     Constructors      =
    //=========================
    public BidListController(BidListService bidListService)
    {
        this.bidListService = bidListService;
    }

    //=========================
    //= Configuration methods =
    //=========================

    @RequestMapping("/bidList/list")
    public String getAllBiddList(Model model)
    {
        model.addAttribute("bidLists", bidListService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid)
    {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String addBid(@Valid BidList bid, BindingResult result, Model model)
    {
        System.out.println("/bidList/validate CALLED");
        if (!result.hasErrors())
        {
            bidListService.save(bid);
            model.addAttribute("bidLists", bidListService.getAll());
            System.out.println("/bidList/validate OK");
            return "redirect:/bidList/list";
        }
        System.out.println("/bidList/validate ERROR");
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        BidList bidList = bidListService.getById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "bidList/update";
        }

        bidList.setBidListId(id);
        bidListService.save(bidList);
        model.addAttribute("bidLists", bidListService.getAll());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model)
    {
        bidListService.deleteById(id);
        model.addAttribute("bidLists", bidListService.getAll());
        return "redirect:/bidList/list";
    }
}