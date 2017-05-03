package com.globalpaysolutions.yovendorecarga.views;

/**
 * Created by Geovanni on 12/02/2017.
 */

public interface PinView
{
    void initialViewsState(String pContent, boolean pUpAsHomeEnabled, boolean pFirtstimeConf);
    void displayPasswordModal();
    void dismissPasswordModal();
    void generateIncorrectPasswText(String pText);
}
