package com.globalpaysolutions.yovendorecarga.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Geovanni on 11/02/2017.
 */


public class OperatorsBalance
{

    @SerializedName("mobileOperator")
    @Expose
    private String mobileOperator;
    @SerializedName("operatorId")
    @Expose
    private Integer operatorId;
    @SerializedName("balance")
    @Expose
    private String balance;

    public String getMobileOperator()
    {
        return mobileOperator;
    }

    public void setMobileOperator(String mobileOperator)
    {
        this.mobileOperator = mobileOperator;
    }

    public Integer getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

}