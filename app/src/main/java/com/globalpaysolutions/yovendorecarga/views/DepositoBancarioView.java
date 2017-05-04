package com.globalpaysolutions.yovendorecarga.views;

import com.globalpaysolutions.yovendorecarga.models.viewmodels.DialogViewModel;

/**
 * Created by Geovanni on 18/03/2017.
 */

public interface DepositoBancarioView
{
    void setUpToolbar();
    void initialViewsStates();
    void showLoading(String pLabel);
    void hideLoading();
    void showResultMessage(DialogViewModel pErrorMessage);
}
