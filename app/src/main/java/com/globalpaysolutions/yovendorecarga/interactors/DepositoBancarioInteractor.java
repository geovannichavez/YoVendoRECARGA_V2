package com.globalpaysolutions.yovendorecarga.interactors;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.globalpaysolutions.yovendorecarga.interactors.interfaces.IDepositoBancarioInteractor;
import com.globalpaysolutions.yovendorecarga.utils.SessionManager;
import com.globalpaysolutions.yovendorecarga.utils.StringsURL;
import com.globalpaysolutions.yovendorecarga.utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geovanni on 18/03/2017.
 */

public class DepositoBancarioInteractor implements IDepositoBancarioInteractor
{
    private static final String TAG = DepositoBancarioInteractor.class.getSimpleName();

    private Context mContext;
    private DepositoBancarioListener mListener;
    private SessionManager mSessionManager;

    public DepositoBancarioInteractor(Context pContext, DepositoBancarioListener pListener)
    {
        mContext = pContext;
        mListener = pListener;
        mSessionManager = new SessionManager(mContext);
    }

    @Override
    public void getBanks()
    {
        VolleySingleton.getInstance(mContext).addToRequestQueue(new JsonObjectRequest(
                Request.Method.GET,
                StringsURL.BANKS,
                null,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("Mensaje JSON ", response.toString());
                mListener.onBankSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                mListener.onBankError(error);
            }
        })
        {
            //Se añade el header para enviar el Token
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Token-Autorization", mSessionManager.getUserToken());
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        }, 1); //Parametro de número de re-intentos
    }

    @Override
    public void sendDepositValidation(String pDepositorName, double pAmount, String pVoucher, int pBankID, String pDate)
    {
        JSONObject voucherValidation = new JSONObject();
        try
        {
            voucherValidation.put("nombre", pDepositorName);
            voucherValidation.put("Banco", pBankID);
            voucherValidation.put("monto", pAmount);
            voucherValidation.put("comprobante", pVoucher);
            voucherValidation.put("fecha", pDate);
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }

        VolleySingleton.getInstance(mContext).addToRequestQueue(new JsonObjectRequest(
                Request.Method.POST,
                StringsURL.DEPOSIT,
                voucherValidation,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d(TAG, response.toString());
                mListener.onDepositValidationSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                mListener.onDepositValidationError(error);
            }
        })
        {
            //Se añade el header para enviar el Token
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("Token-Autorization", mSessionManager.getUserToken());
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        }, 1); //Parametro, de maximo de re-intentos
    }
}
