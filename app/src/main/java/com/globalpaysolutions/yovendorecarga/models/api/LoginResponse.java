package com.globalpaysolutions.yovendorecarga.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Geovanni on 11/02/2017.
 */

public class LoginResponse
{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("AvailableAmount")
    @Expose
    private String availableAmount;
    @SerializedName("SesionID")
    @Expose
    private Integer sesionID;
    @SerializedName("VendorM")
    @Expose
    private Boolean vendorM;
    @SerializedName("CountryID")
    @Expose
    private String countryID;
    @SerializedName("ISO3Code")
    @Expose
    private String iSO3Code;
    @SerializedName("PhoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("VendorCode")
    @Expose
    private Integer vendorCode;
    @SerializedName("ProfileCompleted")
    @Expose
    private Boolean profileCompleted;
    @SerializedName("OperatorsBalance")
    @Expose
    private List<OperatorsBalance> operatorsBalance = null;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getAvailableAmount()
    {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount)
    {
        this.availableAmount = availableAmount;
    }

    public Integer getSesionID()
    {
        return sesionID;
    }

    public void setSesionID(Integer sesionID)
    {
        this.sesionID = sesionID;
    }

    public Boolean getVendorM()
    {
        return vendorM;
    }

    public void setVendorM(Boolean vendorM)
    {
        this.vendorM = vendorM;
    }

    public String getCountryID()
    {
        return countryID;
    }

    public void setCountryID(String countryID)
    {
        this.countryID = countryID;
    }

    public String getISO3Code()
    {
        return iSO3Code;
    }

    public void setISO3Code(String iSO3Code)
    {
        this.iSO3Code = iSO3Code;
    }

    public String getPhoneCode()
    {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode)
    {
        this.phoneCode = phoneCode;
    }

    public Integer getVendorCode()
    {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode)
    {
        this.vendorCode = vendorCode;
    }

    public Boolean getProfileCompleted()
    {
        return profileCompleted;
    }

    public void setProfileCompleted(Boolean profileCompleted)
    {
        this.profileCompleted = profileCompleted;
    }

    public List<OperatorsBalance> getOperatorsBalance()
    {
        return operatorsBalance;
    }

    public void setOperatorsBalance(List<OperatorsBalance> operatorsBalance)
    {
        this.operatorsBalance = operatorsBalance;
    }

}