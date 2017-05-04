package com.globalpaysolutions.yovendorecarga.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class SessionManager
{
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    private Context mContext;
    private int PRIVATE_MODE = 0;

    /*  PREFERENCIAS DEL PIN    */
    SharedPreferences mPinPreferences;
    SharedPreferences.Editor mPinEditor;

    /*  PREFERENCIAS DE NOTIFICACIONES  */
    SharedPreferences NotificationsSettings;
    SharedPreferences.Editor NotificationsEditor;

    /*  ENCRIPTACION/DECRIPTACION  */
    String KEY = "8080808080808089"; //llave
    String IV = "8080808080808090"; // vector de inicialización


    private static final String PREF_NAME = "yvsPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_BALANCE = "availableBalance";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_TOKEN = "userToken";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_REMEMBER_EMAIL = "rememberEmail";
    private static final String KEY_PPW = "userOtherWayConffirmation";
    private static final String KEY_SESSION_ID = "sessionID";
    private static final String KEY_VENDOR_M = "vendorM";
    private static final String KEY_COUNTRY_ID = "countryId";
    private static final String KEY_ISO3_CODE = "iso3Code";
    private static final String KEY_PHONE_CODE = "PhoneCode";
    private static final String KEY_VENDOR_CODE = "VendorCode";
    public static final String KEY_SHOWCASE_SHOWN = "ShowcaseViewShown";
    private static final String KEY_DEVICE_IP_ADDRESS = "device_ip_address";
    private static final String KEY_DEVICE_ID = "device_id";

    private static final String KEY_ACTIVATE_PIN = "securityPin";
    private static final String KEY_PIN_CODE = "pinCode";

    public SessionManager(Context pContext)
    {
        this.mContext = pContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPreferences.edit();

        //Obtiene las preferencias guardadas en las Preferences
        mPinPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPinEditor = mPinPreferences.edit();

    }

    /*
    * **************************************************
    *
    *   SAVE
    *
    * *************************************************
    */


    public void saveDeviceIpAddress(String pAddress)
    {
        mEditor.putString(KEY_DEVICE_IP_ADDRESS, pAddress);
        mEditor.commit();
    }

    public void saveDeviceID(String pDeviceId)
    {
        mEditor.putString(KEY_DEVICE_ID, pDeviceId);
        mEditor.commit();
    }

    public void saveUserPassword(String pPass)
    {
        mEditor.putString(KEY_PPW, pPass);
        mEditor.commit();
    }

    public void saveUserToken(String pToken)
    {
        mEditor.putString(KEY_TOKEN, pToken);
        mEditor.commit();
    }

    public void saveUserPin(String pUserPin)
    {
        mPinEditor.putString(KEY_PIN_CODE, pUserPin);
        mPinEditor.commit();
    }

    public void saveRememberEmailValue(boolean pRemember)
    {
        mEditor.putBoolean(KEY_REMEMBER_EMAIL, pRemember);
        mEditor.commit();
    }

    public void setPinActive(boolean pActive)
    {
        mPinEditor.putBoolean("KEY_ACTIVATE_PIN", pActive);
        mPinEditor.commit();
    }


    /*
    * **************************************************
    *
    *   RETRIEVE
    *
    * *************************************************
    */

    public String getDeviceIpAddress()
    {
        return mPreferences.getString(KEY_DEVICE_IP_ADDRESS, "");
    }

    public String getDeviceID()
    {
        return mPreferences.getString(KEY_DEVICE_ID, "");
    }

    public String getUserPassword()
    {
        return mPreferences.getString(KEY_PPW, "");
    }

    public String getUserToken()
    {
        return mPreferences.getString(KEY_TOKEN, "");

    }

    public String getUserPin()
    {
        return mPinPreferences.getString(KEY_PIN_CODE, "");
    }

    public boolean getRememberEmailValue()
    {
        return mPreferences.getBoolean(KEY_REMEMBER_EMAIL, false);
    }

    public String getUserEmail()
    {
        return mPreferences.getString(KEY_USER_EMAIL, "");
    }

    public boolean isUserPinActive()
    {
        return mPinPreferences.getBoolean("KEY_ACTIVATE_PIN", false);
    }


    /*
    *
    *
    * SESION DEL USUARIO
    *
    */

    //Guarda los datos de la sesión
    public void createLoginSession(String pEmail, String pToken, String pBalance, int pSessionID, boolean pVendorM,
                                   String pCountryID, String pISO3code, String pPhoneCode, int pVendorCode)
    {
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(KEY_USER_EMAIL, pEmail);
        mEditor.putString(KEY_TOKEN, pToken);
        mEditor.putString(KEY_BALANCE, pBalance);
        mEditor.putInt(KEY_SESSION_ID, pSessionID);
        mEditor.putBoolean(KEY_VENDOR_M, pVendorM);
        mEditor.putString(KEY_COUNTRY_ID, pCountryID);
        mEditor.putString(KEY_ISO3_CODE, pISO3code);
        mEditor.putString(KEY_PHONE_CODE, pPhoneCode);
        mEditor.putInt(KEY_VENDOR_CODE, pVendorCode);
        mEditor.commit();
    }

    /*
    *
    *
    *   PERFIL DEL USUARIO
    *
    */
    //Guarda el nombre y apellido del usuario
    public void saveUserProfile(String pFirstname, String pLastname)
    {
        mEditor.putString(KEY_FIRST_NAME, pFirstname);
        mEditor.putString(KEY_LAST_NAME, pLastname);
        mEditor.commit();
    }
}
