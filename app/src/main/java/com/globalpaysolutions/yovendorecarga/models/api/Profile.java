package com.globalpaysolutions.yovendorecarga.models.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Geovanni on 11/02/2017.
 */

public class Profile
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("middle_name")
    @Expose
    private Object middleName;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("verified")
    @Expose
    private String verified;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("vendorId")
    @Expose
    private Integer vendorId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Object getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(Object middleName)
    {
        this.middleName = middleName;
    }

    public Object getGender()
    {
        return gender;
    }

    public void setGender(Object gender)
    {
        this.gender = gender;
    }

    public String getVerified()
    {
        return verified;
    }

    public void setVerified(String verified)
    {
        this.verified = verified;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Object getCategory()
    {
        return category;
    }

    public void setCategory(Object category)
    {
        this.category = category;
    }

    public Integer getVendorId()
    {
        return vendorId;
    }

    public void setVendorId(Integer vendorId)
    {
        this.vendorId = vendorId;
    }

}