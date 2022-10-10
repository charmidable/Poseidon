package com.nnk.springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.nnk.springboot.service.JWTService;
import com.nnk.springboot.repositories.UserRepository;

@Controller
@RequestMapping("app")
public class LoginController
{
    //=========================
    //=      Attributes       =
    //=========================
    private final UserRepository userRepository;
    private final JWTService     jwtService;

    //=========================
    //=     Constructors      =
    //=========================
    public LoginController(UserRepository userRepository, JWTService jwtService)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    //=========================
    //=  Controller methods   =
    //=========================

//    @GetMapping("login")
//    public ModelAndView loginForm()
//    {
//        ModelAndView mav = new ModelAndView();
////        mav.setViewName("login");
//        mav.setViewName("app/login/basicAuth");
//        return mav;
//    }

    @GetMapping("/login")
    public String loginform()
    {
        return "login";
    }


    @GetMapping("login/basicAuth")
    public ResponseEntity<String> login()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();
        String role = authentication.getAuthorities().toArray()[0].toString();

        String token = jwtService.generateToken(name, role);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

        return new ResponseEntity<String>("login", httpHeaders, HttpStatus.OK);
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