package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.models.DialogViewModel;
import com.globalpaysolutions.yovendorecarga.views.LoginView;

public class Login extends AppCompatActivity implements LoginView
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initialViewsStates()
    {

    }

    @Override
    public void showLoading()
    {

    }

    @Override
    public void hideLoading()
    {

    }

    @Override
    public void showErrorMessage(DialogViewModel pErrorMessage)
    {

    }

    @Override
    public void navigatePIN()
    {

    }

    @Override
    public void navigateHome()
    {

    }
}
