package com.globalpaysolutions.yovendorecarga.views;

import com.globalpaysolutions.yovendorecarga.models.DialogViewModel;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public interface LoginView
{
    void initialViewsStates();
    void showLoading();
    void hideLoading();
    void showErrorMessage(DialogViewModel pErrorMessage);
    void navigatePIN();
    void navigateHome();
}
