package com.globalpaysolutions.yovendorecarga.interactors.interfaces;

import com.globalpaysolutions.yovendorecarga.interactors.LoginListener;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public interface ILoginInteractor
{
    void getDeviceIP(LoginListener pListener);
    void attemptLogin(LoginListener pListener, String pEmail, String pPassword, String pDeviceId, String pIPAddress);
    void getUserProfile(LoginListener pListener, String pToken);
}
