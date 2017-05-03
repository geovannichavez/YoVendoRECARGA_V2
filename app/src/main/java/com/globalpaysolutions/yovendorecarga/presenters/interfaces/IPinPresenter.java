package com.globalpaysolutions.yovendorecarga.presenters.interfaces;

/**
 * Created by Geovanni on 12/02/2017.
 */

public interface IPinPresenter
{
    void initialViewsState(String pIntentExtra);
    void checkCredentials();
    void validatePassword(String pPassword);
    void saveSecurityPin(String pPIN, boolean isFirstTime);

}
