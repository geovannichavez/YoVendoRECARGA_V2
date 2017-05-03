package com.globalpaysolutions.yovendorecarga.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.IPinPresenter;
import com.globalpaysolutions.yovendorecarga.ui.activities.Home;
import com.globalpaysolutions.yovendorecarga.utils.SessionManager;
import com.globalpaysolutions.yovendorecarga.views.PinView;

/**
 * Created by Geovanni on 12/02/2017.
 */

public class PinPresenterImpl implements IPinPresenter
{
    private Context mContext;
    private PinView mView;
    private AppCompatActivity mActivity;
    private SessionManager mSessionManager;

    private int mIntentCounter = 0;

    public PinPresenterImpl(Context pContext, AppCompatActivity pActivity, PinView pPinView)
    {
        this.mContext = pContext;
        this.mView = pPinView;
        this.mActivity = pActivity;
        mSessionManager = new SessionManager(mContext);
    }

    @Override
    public void initialViewsState(String pIntentExtra)
    {
        switch (pIntentExtra)
        {
            case "CHANGE_PIN":
                mView.initialViewsState(mContext.getString(R.string.content_change_pin), true, false);
                break;
            case "SET_FIRST_TIME":
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), false, true);
                break;
            case "SET_NEW_EMAIL_PIN":
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), false, true);
                break;
            default:
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), true, false);
                break;
        }
    }

    @Override
    public void checkCredentials()
    {
        String userPin = mSessionManager.getUserPin();
        if(!TextUtils.isEmpty(userPin))
        {
            if(mSessionManager.isUserPinActive())
            {
                mView.displayPasswordModal();
            }
        }
    }

    @Override
    public void validatePassword(String pPassword)
    {
        if(TextUtils.equals(pPassword, mSessionManager.getUserPassword()))
        {
            mIntentCounter = 0;
            mView.displayPasswordModal();
        }
        else
        {
            if(mIntentCounter < 3)
            {
                mView.generateIncorrectPasswText(mContext.getString(R.string.content_insert_pwd));
                mIntentCounter = mIntentCounter + 1;
            }
            else
            {
                mIntentCounter = 0;
                //sessionManager.LogoutUser(false);
            }
        }


    }

    @Override
    public void saveSecurityPin(String pPIN, boolean isFirstTime)
    {
        mSessionManager.saveUserPin(pPIN);

        if(isFirstTime)
        {
            Intent navigateHome = new Intent(mContext, Home.class);
            mSessionManager.setPinActive(true);

            navigateHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            navigateHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            navigateHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            navigateHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            navigateHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Borra el stack completo de navegación:
            navigateHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

            mContext.startActivity(navigateHome);
            mActivity.finish();

        }
        else
        {
            mActivity.finish();
        }
    }

}
