package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "trade")
public class Trade
{
    //=========================
    //=      Attributes       =
    //=========================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;


    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;


    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;


    @NotNull(message = "This Field cannot be empty")
    @Column(name = "buy_quantity")
    private Double buyQuantity;


    //=========================
    //=     Constructors      =
    //=========================

    public Trade()
    {
    }

    public Trade(String account, String type, double buyQuantity)
    {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }


    //=========================
    //=   Getters & Setters   =
    //=========================

    public Integer getTradeId()
    {
        return tradeId;
    }

    public void setTradeId(Integer tradeId)
    {
        this.tradeId = tradeId;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Double getBuyQuantity()
    {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity)
    {
        this.buyQuantity = buyQuantity;
    }
}
