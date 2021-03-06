package com.globalpaysolutions.yovendorecarga.views;

import com.globalpaysolutions.yovendorecarga.models.viewmodels.DialogViewModel;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public interface LoginView
{
    void initialViewsStates(String pLastEmail);
    void showLoading(String pLabel);
    void hideLoading();
    void showErrorMessage(DialogViewModel pErrorMessage);
    void navigatePIN(String pIntentKey, String pIntentValue);
    void navigateHome();
    void checkPermissions();
}
