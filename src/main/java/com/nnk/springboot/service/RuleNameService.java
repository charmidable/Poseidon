package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.domain.RuleName;


@Service
public class RuleNameService
{//=========================
    //=      Attributes       =
    //=========================
    private final RuleNameRepository ruleNameRepository;

    //=========================
    //=     Constructors      =
    //=========================

    public RuleNameService(RuleNameRepository ruleNameRepository)
    {
        this.ruleNameRepository = ruleNameRepository;
    }

    //=========================
    //=    Service methods    =
    //=========================

    public RuleName save(RuleName ruleName)
    {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName getById(int id)
    {
        return ruleNameRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("RuleName Id: " + id + " does not exist."));
    }

    public List<RuleName> getAll()
    {
        return ruleNameRepository.findAll();
    }

    public void deleteById(int id)
    {
        ruleNameRepository.deleteById(id);
    }
}
