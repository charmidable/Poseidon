package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.repositories.UserRepository;

@Controller
public class LoginController
{
    //=========================
    //=      Attributes       =
    //=========================
    private final UserRepository userRepository;

    //=========================
    //=     Constructors      =
    //=========================
    public LoginController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //=========================
    //=  Controller methods   =
    //=========================

    @GetMapping("login")
    public ModelAndView login()
    {
        System.out.println("login() CALLED");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("logout")
    public ModelAndView logout()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles()
    {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error()
    {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}