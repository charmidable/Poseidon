package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.Exception.EntityDoesNotExistException;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.domain.CurvePoint;


@Service
public class CurvePointService
{
    //=========================
    //=      Attributes       =
    //=========================
    private final CurvePointRepository curvePointRepository;

    //=========================
    //=     Constructors      =
    //=========================

    public CurvePointService(CurvePointRepository curvePointRepository)
    {
        this.curvePointRepository = curvePointRepository;
    }

    //=========================
    //=    Service methods    =
    //=========================

    public CurvePoint save(CurvePoint curvePoint)
    {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint getById(int id)
    {
        return curvePointRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("CurvePoint Id: " + id + " does not exist."));
    }

    public List<CurvePoint> getAll()
    {
        return curvePointRepository.findAll();
    }

    public void deleteById(int id)
    {
        curvePointRepository.deleteById(id);
    }
}
