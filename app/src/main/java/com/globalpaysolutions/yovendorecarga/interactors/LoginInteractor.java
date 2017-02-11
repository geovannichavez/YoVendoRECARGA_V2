package com.globalpaysolutions.yovendorecarga.interactors;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.globalpaysolutions.yovendorecarga.interactors.interfaces.ILoginInteractor;
import com.globalpaysolutions.yovendorecarga.utils.DeviceName;
import com.globalpaysolutions.yovendorecarga.utils.StringsURL;
import com.globalpaysolutions.yovendorecarga.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class LoginInteractor implements ILoginInteractor
{
    private static final String TAG = LoginInteractor.class.getSimpleName();
    private Context mContext;

    public LoginInteractor(Context pContext)
    {
        this.mContext = pContext;
    }

    @Override
    public void getDeviceIP(final LoginListener pListener)
    {
        String RequestURL = StringsURL.IP_ADDRESS;

        VolleySingleton.getInstance(mContext).addToRequestQueue(new JsonObjectRequest(
                Request.Method.GET,
                RequestURL, null,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                pListener.onIPAddressSuccess(response);
                Log.i(TAG, response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pListener.onIPAddressError(error);
                Log.i(TAG, error.getMessage());
            }
        })
        {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        }, 1);
    }

    @Override
    public void attemptLogin(final LoginListener pListener, String pEmail, String pPassword, String pDeviceId, String pIPAddress)
    {
        JSONObject jObject = new JSONObject();

        String deviceName = getDeviceName();
        //String encryptedPass = EncryptedPass(KEY, IV, pPass);
        //EncrptdPwd = encryptedPass;

        try
        {
            jObject.put("email", pEmail);
            jObject.put("password", pPassword);
            jObject.put("deviceInfo", deviceName);
            jObject.put("deviceIP", pIPAddress);
            jObject.put("deviceID", pDeviceId);
            System.out.println(jObject);
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }

        VolleySingleton.getInstance(mContext).addToRequestQueue(new JsonObjectRequest(
                Request.Method.POST,
                StringsURL.SIGNIN,
                jObject,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                pListener.onLoginSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pListener.onError(error);
            }
        }), 0);
    }



    /*
    **************************************
    *
    *   OTROS METODOS
    *
    **************************************
    */

    private String getDeviceName()
    {
        String Manufacturer = Build.MANUFACTURER;
        String Model = DeviceName.getDeviceName();
        String versionRelease = Build.VERSION.RELEASE;

        Manufacturer = Manufacturer.substring(0, 1).toUpperCase() + Manufacturer.substring(1).toLowerCase();

        return Manufacturer + " " + Model + " (Android " + versionRelease + ")";
    }
}
