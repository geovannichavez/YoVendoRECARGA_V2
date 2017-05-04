package com.globalpaysolutions.yovendorecarga.interactors.interfaces;

/**
 * Created by Geovanni on 18/03/2017.
 */

public interface IDepositoBancarioInteractor
{
    void getBanks();
    void sendDepositValidation(String pDepositorName, double pAmount, String pVoucher, int pBankID, String pDate);
}
