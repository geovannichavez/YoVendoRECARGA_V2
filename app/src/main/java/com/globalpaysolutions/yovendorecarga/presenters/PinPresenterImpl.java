package com.globalpaysolutions.yovendorecarga.presenters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.presenters.interfaces.IPinPresenter;
import com.globalpaysolutions.yovendorecarga.utils.SessionManager;
import com.globalpaysolutions.yovendorecarga.views.PinView;

/**
 * Created by Geovanni on 12/02/2017.
 */

public class PinPresenterImpl implements IPinPresenter
{
    private Context mContext;
    private PinView mView;
    private SessionManager mSessionManager;

    public PinPresenterImpl(Context pContext, AppCompatActivity pActivity, PinView pPinView)
    {
        this.mContext = pContext;
        this.mView = pPinView;
        mSessionManager = new SessionManager(mContext);
    }

    @Override
    public void initialViewsState(String pIntentExtra)
    {
        switch (pIntentExtra)
        {
            case "CHANGE_PIN":
                mView.initialViewsState(mContext.getString(R.string.content_change_pin), true);
                break;
            case "SET_FIRST_TIME":
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), false);
                break;
            case "SET_NEW_EMAIL_PIN":
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), false);
                break;
            default:
                mView.initialViewsState(mContext.getString(R.string.content_insert_pin), true);
                break;
        }
    }

    @Override
    public void saveSecurityPin()
    {

    }

    @Override
    public void validateIntents()
    {

    }
}
