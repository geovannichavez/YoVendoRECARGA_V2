package com.globalpaysolutions.yovendorecarga.interactors;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Geovanni on 18/03/2017.
 */

public interface DepositoBancarioListener
{
    void onDepositValidationSuccess(JSONObject response);
    void onDepositValidationError(VolleyError errorResponse);
    void onBankSuccess(JSONObject response);
    void onBankError(VolleyError errorResponse);

}
