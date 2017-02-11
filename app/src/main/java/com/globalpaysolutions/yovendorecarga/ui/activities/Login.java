package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.models.DialogViewModel;
import com.globalpaysolutions.yovendorecarga.presenters.LoginPresenterImpl;
import com.globalpaysolutions.yovendorecarga.utils.Validation;
import com.globalpaysolutions.yovendorecarga.views.LoginView;

import java.util.UUID;

public class Login extends AppCompatActivity implements LoginView
{
    //Static variables
    private final static String TAG = Login.class.getCanonicalName();
    final private int REQUEST_READ_PHONE_STATE = 2;

    //Activity Layouts and Views
    EditText etEmail;
    EditText etPassword;
    CheckBox chkRemember;
    ProgressDialog progressDialog;

    //MVP
    LoginPresenterImpl mPresenter;

    //Activity Global Variables
    Validation mValidator;
    TelephonyManager mTelephonyManager;

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
        mTelephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        mPresenter.setInitialViewState();
        mPresenter.checkPermissions();
        mPresenter.getPublicIPAddress();
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

    @Override
    public void checkPermissions()
    {
        final String tmDevice;
        final String tmSerial;
        final String androidId;
        String deviceID = "";
        UUID deviceUuid = null;

        try
        {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED)
            {
                if(Build.VERSION.SDK_INT >= 23)
                {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE))
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                        alertDialog.setTitle("PERMISOS");
                        alertDialog.setMessage(getString(R.string.allow_permission_phone_state_dialog));
                        alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                            }
                        });
                        alertDialog.show();
                    }
                }
                else
                {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                }

            }
            else
            {
                tmDevice = "" + mTelephonyManager.getDeviceId();
                tmSerial = "" + mTelephonyManager.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                deviceID = deviceUuid.toString().toUpperCase();

                //Guarda el ID del dispositivo
                mPresenter.saveDeviceID(deviceID);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Log.i(TAG, "Device id: " + deviceID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    String deviceId = this.mPresenter.getDeviceID();
                    this.mPresenter.saveDeviceID(deviceId);
                }
                else
                {
                    if(Build.VERSION.SDK_INT >= 23)
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                        alertDialog.setTitle("PERMISOS");
                        alertDialog.setMessage(getString(R.string.allow_permission_phone_state_dialog));
                        alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                            }
                        });
                        alertDialog.show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
