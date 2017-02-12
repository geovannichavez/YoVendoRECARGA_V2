package com.globalpaysolutions.yovendorecarga.presenters.interfaces;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public interface ILoginPresenter
{
    void setInitialViewState();
    void checkPermissions();
    void getPublicIPAddress();
    void saveDeviceID(String pDeviceId);
    String getDeviceID();
    void attemptLogin(String pEmail, String pPassword);
}
