package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.models.DialogViewModel;
import com.globalpaysolutions.yovendorecarga.presenters.LoginPresenterImpl;
import com.globalpaysolutions.yovendorecarga.utils.Validation;
import com.globalpaysolutions.yovendorecarga.views.LoginView;

public class Login extends AppCompatActivity implements LoginView
{
    private final static String TAG = Login.class.getCanonicalName();

    //Activity Layouts and Views
    EditText etEmail;
    EditText etPassword;
    CheckBox chkRemember;
    ProgressDialog progressDialog;

    //MVP
    LoginPresenterImpl mPresenter;

    //Activity Global Variables
    Validation mValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etRegMail);
        etPassword = (EditText) findViewById(R.id.etRegPass);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);

        mValidator = new Validation(Login.this);
        mPresenter = new LoginPresenterImpl(this, this, this);

        mPresenter.setInitialViewState();
    }

    @Override
    public void initialViewsStates()
    {
        try
        {
            etEmail.setText("");
            etPassword.setText("");
            chkRemember.setChecked(true);

            mValidator.initializeEmailValidator(etEmail);
            mValidator.initializePasswordValidator(etPassword);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void showLoading(String pLabel)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(pLabel);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void hideLoading()
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
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
