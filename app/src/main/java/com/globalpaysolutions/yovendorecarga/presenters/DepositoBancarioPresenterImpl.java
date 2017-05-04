package com.globalpaysolutions.yovendorecarga.presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.interactors.DepositoBancarioInteractor;
import com.globalpaysolutions.yovendorecarga.interactors.DepositoBancarioListener;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.IDepositoBancarioPresenter;
import com.globalpaysolutions.yovendorecarga.views.DepositoBancarioView;

import org.json.JSONObject;

/**
 * Created by Geovanni on 18/03/2017.
 */

public class DepositoBancarioPresenterImpl implements IDepositoBancarioPresenter, DepositoBancarioListener
{
    private Context mContext;
    private DepositoBancarioView mView;
    private DepositoBancarioInteractor mInteractor;

    public DepositoBancarioPresenterImpl(Context pContext, AppCompatActivity pActivity, DepositoBancarioView pView)
    {
        mContext = pContext;
        mView = pView;
        mInteractor = new DepositoBancarioInteractor(mContext, this);
    }

    @Override
    public void initialViewsState()
    {
        mView.initialViewsStates();
    }

    @Override
    public void requestBanks()
    {
        if(checkConnection())
            mInteractor.getBanks();
    }

    @Override
    public void onDepositValidationSuccess(JSONObject response)
    {

    }

    @Override
    public void onDepositValidationError(VolleyError errorResponse)
    {

    }

    @Override
    public void onBankSuccess(JSONObject response)
    {

    }

    @Override
    public void onBankError(VolleyError errorResponse)
    {

    }


    /*
    *
    *
    *   OTROS METODOS
    *
    *
    */
    private boolean checkConnection()
    {
        boolean connected = true;
        if(!hasNetworkConnection())
        {
            connected = false;
            String connectionMessage = mContext.getResources().getString(R.string.no_internet_connection);
            Toast.makeText(mContext, connectionMessage, Toast.LENGTH_LONG).show();
        }
        return connected;
    }

    private boolean hasNetworkConnection()
    {
        boolean isConnectedWifi = false;
        boolean isConnectedMobile = false;

        try
        {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null)
            {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    isConnectedWifi = true;
                }
                else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    isConnectedMobile = true;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return isConnectedWifi || isConnectedMobile;
    }


}
