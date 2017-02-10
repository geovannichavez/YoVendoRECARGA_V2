package com.globalpaysolutions.yovendorecarga.utils;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public class StringsURL
{
    //Servidor para PRODUCCION
    //public final static String URL_BASE = "http://csncusgats.cloudapp.net:82/v1/";

    //PRE-PRODUCCIÓN (Enviando recargas de prueba)
    public final static String URL_BASE = "http://csncusgats.cloudapp.net:8074/v1/";

    //Servidor para DESARROLLOm
    //public final static String URL_BASE = "http://csncusgats.cloudapp.net:8073/v1/";

    //Direccion con cifrado
    //private final static String URL_BASE = "https://api.yovendorecarga.com/";

    //  :::::   LOCALHOST API   :::::
    //public final static String URL_BASE = "http://10.0.2.2:49435/";

    //:::::   LOCALHOST API   :::::
    //public final static String URL_BASE = "http://192.168.1.32:8084/";

    public final static String SIGNIN = URL_BASE + "signin/";

    public final static String AUTH_SIGNIN = URL_BASE + "auth/signin/";

    public final static String TOPUP = URL_BASE + "topup/";

    public final static String PASSWORD = URL_BASE + "password";

    public final static String HISTORY_GMT0 = URL_BASE + "history/gmt0";

    public final static String HISTORY = URL_BASE + "history";

    public final static String PROFILE = URL_BASE + "profile";

    public final static String DEPOSIT = URL_BASE + "deposito";

    public final static String TOPUPPAYMENT = URL_BASE + "topuppayment";

    public final static String SIGNOUT = URL_BASE + "signout";

    public final static String USERBAG = URL_BASE + "userbag";

    public final static String PRODUCTS = URL_BASE + "products/";

    public final static String DEVICEREGISTRATION = URL_BASE + "deviceregistration/";

    public final static String NOTIFICATIONSHISTORY = URL_BASE + "getnotifications/";

    public final static String BALANCREQUEST = URL_BASE + "balancerequest";

    public final static String BANKS = URL_BASE + "banks/";

    public final static String OPERATORS = URL_BASE + "operators/";

    public final static String USER_NOTIFICATIONS = URL_BASE + "getUserNotifications/";

    public final static String REFERRED = URL_BASE + "referred/";


    /**
     *
     *
     *  CEOAnalytics API
     *
     **/

    //Desarrollo
    //public final static String CEO_ANALYTICS_URL_BASE = "http://csncusgats.cloudapp.net:8076/v1/";

    //Pre-Produccion
    public final static String CEO_ANALYTICS_URL_BASE = "http://csncusgats.cloudapp.net:8077/v1/";

    public static String CEO_ANALYTICS_APIKEY = "a1bXuav8tqtXQWPALOhdQJvAQ1IK8esE4d6YYGBk+qc=";

    public final static String CEOA_NOTIFICATIONS_HISTORY = CEO_ANALYTICS_URL_BASE + "notificationshistory/android";

    public final static String CEOA_DEVICE_DATA = CEO_ANALYTICS_URL_BASE + "api/devicedata";

    public final static String CEO_MARK_NOTIFICATION_READ = CEO_ANALYTICS_URL_BASE + "setNotificationRead/";


}
