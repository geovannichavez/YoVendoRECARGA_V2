package com.globalpaysolutions.yovendorecarga.views;

/**
 * Created by Geovanni on 12/02/2017.
 */

public interface PinView
{
    void initialViewsState(String pContent, boolean pUpAsHomeEnabled);
    void generateIncorrectPINText(String pText);
    void generateIncorrectPasswText(String pText);
}
