package com.globalpaysolutions.yovendorecarga.presenters.interfaces;

/**
 * Created by Geovanni on 12/02/2017.
 */

public interface IPinPresenter
{
    void initialViewsState(String pIntentExtra);
    void saveSecurityPin();
    void validateIntents();
}
