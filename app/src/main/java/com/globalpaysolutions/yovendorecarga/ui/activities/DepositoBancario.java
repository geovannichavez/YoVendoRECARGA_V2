package com.globalpaysolutions.yovendorecarga.ui.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.globalpaysolutions.yovendorecarga.R;
import com.globalpaysolutions.yovendorecarga.models.viewmodels.DialogViewModel;
import com.globalpaysolutions.yovendorecarga.presenters.DepositoBancarioPresenterImpl;
import com.globalpaysolutions.yovendorecarga.utils.Validation;
import com.globalpaysolutions.yovendorecarga.views.DepositoBancarioView;

import java.util.Date;

public class DepositoBancario extends AppCompatActivity implements DepositoBancarioView
{
    //Views and Layouts
    private TextView tvFechaDeposito;
    private Button btnEnviarDeposito;
    private Spinner spBanks;
    private EditText edNombreDepositante;
    private EditText edMonto;
    private EditText edComprobante;
    private ProgressDialog progressDialog;

    //MVP
    DepositoBancarioPresenterImpl mPresenter;

    //Global variables
    int mMinDepositLength;
    boolean mDepositDateSet;
    boolean mBankSelected;
    Date mDepositDate;
    Validation mValidator;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito_bancario);

        mValidator = new Validation(this);
        mPresenter = new DepositoBancarioPresenterImpl(this, this, this);

        edNombreDepositante = (EditText) findViewById(R.id.edDepositante);
        edMonto = (EditText) findViewById(R.id.edMonto);
        edComprobante = (EditText) findViewById(R.id.edComprobante);
        tvFechaDeposito = (TextView) findViewById(R.id.tvFechaDeposito);
        spBanks = (Spinner) findViewById(R.id.spBanks);
        btnEnviarDeposito = (Button) findViewById(R.id.btnEnviarDeposito);

        mPresenter.initialViewsState();

    }

    @Override
    public void setUpToolbar()
    {
        try
        {
            Toolbar toolbar;
            toolbar = (Toolbar) findViewById(R.id.toolbarPin);
            toolbar.setTitle(getString(R.string.title_activity_menu_pin));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialViewsStates()
    {
        edNombreDepositante.setText("");
        mBankSelected = false;
        edMonto.setText("");
        edComprobante.setText("");
        mDepositDateSet = false;
        mDepositDate = null;
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
    public void showResultMessage(DialogViewModel pErrorMessage)
    {

    }

    /*
    *
    *
    *   OTROS METODOS
    *
    */

    private boolean checkValidation()
    {
        boolean valid = true;
        if(!mValidator.isValidName(edNombreDepositante, true))
            valid = false;

        if(!mBankSelected)
            valid = false;

        if(!mValidator.isValidAmount(edMonto, true))
            valid = false;

        if(!mValidator.isValidVoucher(edComprobante, true))
            valid = false;

        if(!mValidator.isValidMinLength(edComprobante, mMinDepositLength))
            valid = false;

        if(!mDepositDateSet)
            valid = false;

        if(mValidator.isValidDepositDate(mDepositDate))
            valid  = false;

        return valid;
    }
}
