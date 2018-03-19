package com.thm.fcm_example.app;

/**
 * Created by thm on 08/03/2018.
 */
public class Constant {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    // Shared pref constant
    public static final String SHARED_PREF = "firebase";
    public static final String REGISTER_ID = "regId";


    // channelId for Notification compat
    public static final String CHANNEL_ID = "channelId";

    public static final String END_POINT_URL = "http://192.168.1.70:3000";
}