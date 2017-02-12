package com.globalpaysolutions.yovendorecarga.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Geovanni on 11/02/2017.
 */

public class ProfileResponse
{
    @SerializedName("profile")
    @Expose
    private Profile profile;

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }
}
