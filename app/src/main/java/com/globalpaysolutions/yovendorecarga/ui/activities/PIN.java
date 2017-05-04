package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.presenters.PinPresenterImpl;
import com.globalpaysolutions.yovendorecarga.utils.Constants;
import com.globalpaysolutions.yovendorecarga.utils.Validation;
import com.globalpaysolutions.yovendorecarga.views.PinView;

public class PIN extends AppCompatActivity implements PinView
{
    //Views and Layouts
    Toolbar toolbar;
    EditText etPincode;
    EditText etConfirmPincode;
    TextView tvPinDialogTitle;
    TextView tvContentPin;
    EditText etPin;
    EditText etEnterPww;
    AlertDialog mPinDialog;

    //MVP
    PinPresenterImpl mPresenter;

    //Activity Global Variables
    Validation mValidator;
    String mIntentExtra = "";
    boolean mFirstTimeConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        mIntentExtra = getIntent().getStringExtra("PIN_CONF");

        toolbar = (Toolbar) findViewById(R.id.toolbarPin);
        toolbar.setTitle(getString(R.string.title_activity_menu_pin));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mValidator = new Validation(this);
        mPresenter = new PinPresenterImpl(this, this, this);

        tvContentPin = (TextView) findViewById(R.id.tvContentPin);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etConfirmPincode = (EditText) findViewById(R.id.etConfirmPincode);

        mPresenter.initialViewsState(mIntentExtra);
        mPresenter.checkCredentials();

    }

    @Override
    public void initialViewsState(String pContent, boolean pUpAsHomeEnabled, boolean pFirtstimeConf)
    {
        try
        {
            mValidator.setPinInputFormatter(etPincode, etConfirmPincode);

            getSupportActionBar().setDisplayHomeAsUpEnabled(pUpAsHomeEnabled);
            tvContentPin.setText(pContent);
            mFirstTimeConfiguration = pFirtstimeConf;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void displayPasswordModal()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PIN.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_pin_dialog, null);

        builder.setView(v);

        Button btnAccept = (Button) v.findViewById(R.id.btnAccept);

        tvPinDialogTitle = (TextView) v.findViewById(R.id.tvPinDialogTitle);
        tvPinDialogTitle.setText(getString(R.string.title_insert_pwd));
        tvContentPin.setText(getString(R.string.content_insert_pwd));

        etPin = (EditText) v.findViewById(R.id.etEnterPin);
        etPin.setVisibility(View.GONE);

        //EditText donde se va a insertar la password
        etEnterPww = (EditText) v.findViewById(R.id.etEnterPww);
        etEnterPww.setTypeface(Typeface.DEFAULT);
        etEnterPww.setVisibility(View.VISIBLE);
        etEnterPww.setHint(getString(R.string.title_insert_pwd));



        mPinDialog = builder.show();

        btnAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String enteredPassword = etEnterPww.getText().toString().trim();
                mPresenter.validatePassword(enteredPassword);
            }
        });

        mPinDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog)
            {
                PIN.this.finish();
            }
        });

        builder.create();
    }

    @Override
    public void dismissPasswordModal()
    {
        try
        {
            mPinDialog.dismiss();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void generateIncorrectPasswText(String pText)
    {
        etEnterPww.setText("");
        tvContentPin.setText(pText);
    }

    public void SetPinCode(View view)
    {
        if (checkValidation())
        {
            String userPinCode = etPincode.getText().toString().trim();
            mPresenter.saveSecurityPin(userPinCode, mFirstTimeConfiguration);
        }
    }

    private boolean checkValidation()
    {
        boolean valid = true;

        if(!mValidator.isValidPIN(etPincode, true))
        {
            valid = false;
        }

        if(!mValidator.isValidPIN(etConfirmPincode, true))
        {
            valid = false;
        }

        if (!mValidator.PinCodesMatch(etConfirmPincode, etPincode))
        {
            valid = false;
        }

        return valid;
    }
}
