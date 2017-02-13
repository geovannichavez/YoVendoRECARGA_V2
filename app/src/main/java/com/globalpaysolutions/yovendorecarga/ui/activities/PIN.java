package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.presenters.PinPresenterImpl;
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

    //Activity Global Variables
    Validation mValidator;
    PinPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        String IntentExtra = getIntent().getStringExtra("PIN_CONF");

        toolbar = (Toolbar) findViewById(R.id.toolbarPin);
        toolbar.setTitle(getString(R.string.title_activity_menu_pin));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mValidator = new Validation(this);
        mPresenter = new PinPresenterImpl(this, this, this);


        tvContentPin = (TextView) findViewById(R.id.tvContentPin);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etConfirmPincode = (EditText) findViewById(R.id.etConfirmPincode);



        mPresenter.initialViewsState(IntentExtra);


        /*if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras != null)
            {
                FirstTimeConfiguration= extras.getBoolean("FIRST_TIME_CONFIGURATION");
            }
        }
        else
        {
            FirstTimeConfiguration= (boolean) savedInstanceState.getSerializable("FIRST_TIME_CONFIGURATION");
        }*/



    }

    @Override
    public void initialViewsState(String pContent, boolean pUpAsHomeEnabled)
    {
        try
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(pUpAsHomeEnabled);
            tvContentPin.setText(pContent);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void generateIncorrectPINText(String pText)
    {

    }

    @Override
    public void generateIncorrectPasswText(String pText)
    {

    }
}
