package com.globalpaysolutions.yovendorecarga.presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.interactors.LoginInteractor;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.ILoginPresenter;
import com.globalpaysolutions.yovendorecarga.views.LoginView;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class LoginPresenterImpl implements ILoginPresenter
{
    private LoginView mView;
    private LoginInteractor mInteractor;
    private Context mContext;

    public LoginPresenterImpl(LoginView pLoginView, AppCompatActivity pActivity, Context pContext)
    {
        this.mView = pLoginView;
        this.mContext = pContext;
        this.mInteractor = new LoginInteractor(mContext);
    }


    @Override
    public void setInitialViewState()
    {
        this.mView.initialViewsStates();
    }

    @Override
    public void getPublicIPAddress()
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
