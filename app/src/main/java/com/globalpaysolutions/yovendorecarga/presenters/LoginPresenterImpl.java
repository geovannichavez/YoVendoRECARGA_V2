package com.globalpaysolutions.yovendorecarga.presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.interactors.LoginInteractor;
import com.globalpaysolutions.yovendorecarga.interactors.LoginListener;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.ILoginPresenter;
import com.globalpaysolutions.yovendorecarga.utils.SessionManager;
import com.globalpaysolutions.yovendorecarga.views.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class LoginPresenterImpl implements ILoginPresenter, LoginListener
{
    private static final String TAG = LoginPresenterImpl.class.getCanonicalName();

    private LoginView mView;
    private LoginInteractor mInteractor;
    private Context mContext;
    private SessionManager mSessionManager;

    public LoginPresenterImpl(LoginView pLoginView, AppCompatActivity pActivity, Context pContext)
    {
        this.mView = pLoginView;
        this.mContext = pContext;
        this.mInteractor = new LoginInteractor(mContext);
        this.mSessionManager = new SessionManager(mContext);
    }

    @Override
    public void setInitialViewState()
    {
        this.mView.initialViewsStates();
    }

    @Override
    public void checkPermissions()
    {
        this.mView.checkPermissions();
    }

    @Override
    public void getPublicIPAddress()
    {
        if(checkConnection())
        {
            this.mInteractor.getDeviceIP(this);
        }
    }

    @Override
    public void saveDeviceID(String pDeviceId)
    {
        this.mSessionManager.saveDeviceID(pDeviceId);
    }

    @Override
    public String getDeviceID()
    {
        return this.mSessionManager.getDeviceID();
    }

    @Override
    public void onError(VolleyError errorResponse)
    {

    }

    @Override
    public void onLoginSuccess(JSONObject response)
    {

    }

    @Override
    public void onIPAddressSuccess(JSONObject response)
    {
        try
        {
            String ipAddress = response.has("ip") ? response.getString("ip") : "";
            mSessionManager.saveDeviceIpAddress(ipAddress);
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onIPAddressError(VolleyError errorResponse)
    {
        Log.e(TAG, errorResponse.getMessage());
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
