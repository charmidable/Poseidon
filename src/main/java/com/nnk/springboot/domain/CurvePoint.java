package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint
{
    //=========================
    //=      Attributes       =
    //=========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer     id;
    @NotNull(message = "curveId must not be null")
    private Integer     curveId;
    private Timestamp   asOfDate;
    private Double      term;
    private Double      value;
    private Timestamp   creationDate;

    //=========================
    //=     Constructors      =
    //=========================

    public CurvePoint()
    {

    }

    public CurvePoint(int curveId, double term, double value)
    {
        this.term           = term;
        this.value          = value;
        this.curveId        = curveId;
    }

    public CurvePoint(Integer id, Integer curveId, Timestamp asOfDate, Double term, Double value, Timestamp creationDate)
    {
        this.id             = id;
        this.term           = term;
        this.value          = value;
        this.curveId        = curveId;
        this.asOfDate       = asOfDate;
        this.creationDate   = creationDate;
    }

    //=========================
    //=   Getters & Setters   =
    //=========================

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCurveId()
    {
        return curveId;
    }

    public void setCurveId(Integer curveId)
    {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate()
    {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate)
    {
        this.asOfDate = asOfDate;
    }

    public Double getTerm()
    {
        return term;
    }

    public void setTerm(Double term)
    {
        this.term = term;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }
}
