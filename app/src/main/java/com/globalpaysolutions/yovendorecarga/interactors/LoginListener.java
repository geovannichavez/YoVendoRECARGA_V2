package com.globalpaysolutions.yovendorecarga.interactors;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public interface LoginListener
{
    void onError(VolleyError errorResponse);
    void onLoginSuccess(String pEmail, JSONObject response);
    void onIPAddressSuccess(JSONObject response);
    void onIPAddressError(VolleyError errorResponse);
    void onProfileSuccess(JSONObject response);
}
