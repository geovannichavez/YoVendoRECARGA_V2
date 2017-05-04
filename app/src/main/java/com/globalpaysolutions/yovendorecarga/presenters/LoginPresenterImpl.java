package com.globalpaysolutions.yovendorecarga.presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.interactors.LoginInteractor;
import com.globalpaysolutions.yovendorecarga.interactors.LoginListener;
import com.globalpaysolutions.yovendorecarga.models.api.LoginResponse;
import com.globalpaysolutions.yovendorecarga.models.api.Profile;
import com.globalpaysolutions.yovendorecarga.models.api.ProfileResponse;
import com.globalpaysolutions.yovendorecarga.models.viewmodels.DialogViewModel;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.ILoginPresenter;
import com.globalpaysolutions.yovendorecarga.utils.Encrypt;
import com.globalpaysolutions.yovendorecarga.utils.SessionManager;
import com.globalpaysolutions.yovendorecarga.views.LoginView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class LoginPresenterImpl implements ILoginPresenter, LoginListener
{
    private static final String TAG = LoginPresenterImpl.class.getCanonicalName();
    private static final String PIN_CONF = "PIN_CONF";

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
        if(mSessionManager.getRememberEmailValue())
            mView.initialViewsStates(mSessionManager.getUserEmail());
        else
            mView.initialViewsStates("");
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
    public void attemptLogin(String pEmail, String pPassword, boolean pEmailChecked)
    {
        String encryptedPassword = encryptedPassword(Encrypt.KEY, Encrypt.IV, pPassword);
        String deviceIpAddress = mSessionManager.getDeviceIpAddress();
        String deviceId = mSessionManager.getDeviceID();
        this.mSessionManager.saveUserPassword(encryptedPassword);
        this.mSessionManager.saveRememberEmailValue(pEmailChecked);

        mView.showLoading(mContext.getString(R.string.dialog_logging_in));
        mInteractor.attemptLogin(this, pEmail, encryptedPassword, deviceId, deviceIpAddress);
    }

    @Override
    public void onError(VolleyError errorResponse)
    {
        int statusCode = 0;
        NetworkResponse networkResponse = errorResponse.networkResponse;
        DialogViewModel errorMessage = new DialogViewModel();

        mView.hideLoading();

        if(mSessionManager.getRememberEmailValue())
            mView.initialViewsStates(mSessionManager.getUserEmail());
        else
            mView.initialViewsStates("");

        if (networkResponse != null)
        {
            statusCode = networkResponse.statusCode;
        }

        if (errorResponse instanceof TimeoutError || errorResponse instanceof NoConnectionError)
        {
            errorMessage.setTitle(mContext.getString(R.string.we_are_sorry_msg_title));
            errorMessage.setLine1(mContext.getString(R.string.something_went_wrong_try_again));
            errorMessage.setAcceptButton(mContext.getString(R.string.button_accept));
            mView.showErrorMessage(errorMessage);
        }
        else if (errorResponse instanceof ServerError)
        {
            errorMessage.setTitle(mContext.getString(R.string.we_are_sorry_msg_title));
            errorMessage.setLine1(mContext.getString(R.string.something_went_wrong_try_again));
            errorMessage.setAcceptButton(mContext.getString(R.string.button_accept));
            mView.showErrorMessage(errorMessage);
        }
        else if (errorResponse instanceof NetworkError)
        {
            errorMessage.setTitle(mContext.getString(R.string.internet_connecttion_title));
            errorMessage.setLine1(mContext.getString(R.string.internet_connecttion_msg));
            errorMessage.setAcceptButton(mContext.getString(R.string.button_accept));
            mView.showErrorMessage(errorMessage);
        }
        else if (errorResponse instanceof AuthFailureError)
        {
            errorMessage.setTitle(mContext.getString(R.string.error_invalid_credentials_title));
            errorMessage.setLine1(mContext.getString(R.string.error_invalid_credentials_content));
            errorMessage.setAcceptButton(mContext.getString(R.string.button_accept));
            mView.showErrorMessage(errorMessage);
        }
    }

    @Override
    public void onLoginSuccess(String pEmail, JSONObject response)
    {
        try
        {
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);

            this.mSessionManager.createLoginSession(pEmail, loginResponse.getToken(),
                    loginResponse.getAvailableAmount(), loginResponse.getSesionID(), loginResponse.getVendorM(),
                    loginResponse.getCountryID(), loginResponse.getISO3Code(), loginResponse.getPhoneCode(),
                    loginResponse.getVendorCode());

            this.mInteractor.getUserProfile(this, loginResponse.getToken());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
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

    @Override
    public void onProfileSuccess(JSONObject response)
    {
        try
        {
            Gson gson = new Gson();
            ProfileResponse profileResponse = gson.fromJson(response.toString(), ProfileResponse.class);
            Profile profile = profileResponse.getProfile();
            this.mSessionManager.saveUserProfile(profile.getFirstName(), profile.getLastName());

            if(mSessionManager.getUserPin().isEmpty())
            {
                this.mView.navigatePIN(PIN_CONF, "SET_FIRST_TIME");
            }
            else if(!mSessionManager.getUserEmail().equals(profile.getEmail()) || !mSessionManager.getRememberEmailValue())
            {
                this.mView.navigatePIN(PIN_CONF, "SET_NEW_EMAIL_PIN");
            }
            else
            {
                this.mView.navigateHome();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
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

    private String encryptedPassword(String pKey, String pIV, String pClearText)
    {
        String _encrypted = "";
        try
        {
            _encrypted = Encrypt.encrypt(pKey, pIV, pClearText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return _encrypted;
    }

}
