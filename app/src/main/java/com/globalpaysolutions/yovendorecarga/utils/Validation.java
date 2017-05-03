package com.globalpaysolutions.yovendorecarga.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.globalpaysolutions.yovendorecarga.R;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Geovanni on 11/02/2017.
 */

public class Validation
{
    Context mContext;

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{4}-\\d{4}";
    private static final String NAME_REGEX = "^[\\p{L} .'-]+$";
    private static final String USERNAME_REGEX = "^[_A-Za-z0-9-\\+]{3,15}$";
    private static final String AMOUNT_REGEX = "[0-9]+([,.][0-9]{1,2})?";
    private static final String VOUCHER_REGEX = "\\d+$";
    private static final String PIN_REGEX = "\\d{4}";

    private static String REQUIRED_MSG = "Requerido";
    private static String EMAIL_MSG = "Email no valido";
    private static String PHONE_MSG = "5555-5555";
    private static String NAME_MSG = "Debe escribir un nombre válido";
    private static String USERNAME_MSG = "Nombre de usuario no válido";
    private static String AMOUNT_MSG = "No es un monto valido";
    private static String VOUCHER_MSG = "Comprobante no valido";
    private static String PIN_MESSAGE = "PIN no valido";


    public Validation(Context pContext)
    {
        this.mContext = pContext;
        REQUIRED_MSG = mContext.getResources().getString(R.string.validation_required);
        EMAIL_MSG = mContext.getResources().getString(R.string.validation_not_valid_email);
        NAME_MSG = mContext.getResources().getString(R.string.validation_no_valid_name);
        USERNAME_MSG = mContext.getResources().getString(R.string.validation_no_valid_username);
        PHONE_MSG = mContext.getResources().getString(R.string.validation_not_valid_phone);
        AMOUNT_MSG = mContext.getResources().getString(R.string.validation_not_valid_amount);
        VOUCHER_MSG = mContext.getResources().getString(R.string.validation_not_valid_voucher);
    }

    /*
    *
    *   INPUT FORMATTERS
    *
    */

    public void setPinInputFormatter(final EditText pPinInput, final EditText pConfirmPinInput)
    {
        pPinInput.setTransformationMethod(new PasswordTransformationMethod());
        pPinInput.setTypeface(Typeface.DEFAULT);

        pPinInput.addTextChangedListener(new TextWatcher()
        {
            int TextLength = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                String str = pPinInput.getText().toString();
                TextLength = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String PinText = pPinInput.getText().toString();

                //Esconde el teclado después que el EditText alcanzó los 4 dígitos
                if (PinText.length() == 4 && TextLength < PinText.length())
                {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });


        /*
        *     PIN CODE CONFFIRMATION
        */
        pConfirmPinInput.setTransformationMethod(new PasswordTransformationMethod());
        pConfirmPinInput.setTypeface(Typeface.DEFAULT);
        pConfirmPinInput.addTextChangedListener(new TextWatcher()
        {
            int TextLength = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                String str = pConfirmPinInput.getText().toString();
                TextLength = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String PinText = pConfirmPinInput.getText().toString();

                //Esconde el teclado después que el EditText alcanzó los 4 dígitos
                if (PinText.length() == 4 && TextLength < PinText.length())
                {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });
    }

    public void initializePasswordValidator(final EditText pEdittext)
    {
        pEdittext.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pEdittext.setTransformationMethod(new PasswordTransformationMethod());
        pEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    HasText(pEdittext);
                }
            }
        });
    }

    public void initializeEmailValidator(final EditText pEdittext)
    {
        pEdittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        pEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    isEmailAddress(pEdittext, true);
                }
            }
        });
    }

    /*
    *
    *
    * VALIDATORS
    *
    *
    */

    public boolean isEmailAddress(EditText editText, boolean required)
    {
        return IsValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    public boolean Email_IsEmailAddress(EditText editText, boolean required)
    {
        return IsValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }


    public boolean isValidUsername(EditText editText, boolean required)
    {
        return IsValid(editText, USERNAME_REGEX, USERNAME_MSG, required);
    }

    public boolean isValidName(EditText editText, boolean required)
    {
        return IsValid(editText, NAME_REGEX, NAME_MSG, required);
    }

    public boolean isPhoneNumber(EditText editText, boolean required)
    {
        return IsValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    public boolean PasswordsMatch(EditText pEditText1, EditText pEditText2)
    {
        boolean Valid = false;

        if (pEditText1.getText().toString().equals(pEditText2.getText().toString()))
        {
            Valid = true;
            pEditText2.setError(null);
        }
        else
        {
            pEditText1.setError(mContext.getString(R.string.password_not_match));
        }

        return Valid;
    }

    public boolean PinCodesMatch(EditText pEditText1, EditText pEditText2)
    {
        boolean Valid = false;

        if (pEditText1.getText().toString().equals(pEditText2.getText().toString()))
        {
            Valid = true;
            pEditText2.setError(null);
        }
        else
        {
            Toast.makeText(mContext, mContext.getString(R.string.pin_not_match), Toast.LENGTH_LONG).show();
        }

        return Valid;
    }

    public boolean isDifferentPassword(EditText pEditText1, EditText pEditText2)
    {
        boolean Valid = false;

        if (pEditText1.getText().toString().equals(pEditText2.getText().toString()))
        {
            pEditText1.setError(mContext.getString(R.string.diff_password_required));
        }
        else
        {
            Valid = true;
            pEditText1.setError(null);
        }

        return Valid;
    }

    public boolean isValidAmount(EditText editText, boolean required)
    {
        return IsValid(editText, AMOUNT_REGEX, AMOUNT_MSG, required);
    }

    public boolean isValidVoucher(EditText editText, boolean required)
    {
        return IsValid(editText, VOUCHER_REGEX, VOUCHER_MSG, required);
    }

    public boolean isValidMinLength(EditText editText, int pMinLength)
    {
        boolean valid = false;

        editText.setError(null);

        String LengthText = editText.getText().toString();
        if (LengthText.length() >= pMinLength)
        {
            valid = true;
        }
        else
        {
            String strError = String.format(mContext.getResources().getString(R.string.validation_not_valid_minimum), pMinLength);
            editText.setError(strError);
        }

        return valid;

    }

    public boolean isValidDepositDate(Date pDepositDate)
    {
        boolean valid = false;

        if (pDepositDate != null)
        {
            if (pDepositDate.getTime() > new Date().getTime())
            {
                valid = false;
                Toast.makeText(mContext, mContext.getString(R.string.deposit_date_invalid), Toast.LENGTH_LONG).show();
            }
            else
            {
                valid = true;
            }
        }

        return valid;
    }

    public boolean isValidPIN(EditText pEditText, boolean pRequired)
    {
        return IsValid(pEditText, PIN_REGEX, PIN_MESSAGE, pRequired);
    }


    private boolean IsValid(EditText editText, String regex, String errMsg, boolean required)
    {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (required && !HasText(editText))
            return false;

        if (required && !Pattern.matches(regex, text))
        {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    private boolean HasText(EditText editText)
    {
        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (text.length() == 0)
        {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }



}
