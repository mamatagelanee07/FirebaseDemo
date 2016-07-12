package com.andyland.firebasedemo.service.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Andy
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = FirebaseInstanceIDService.class.getSimpleName();

    /**
     * The registration token may change when:
     * The app deletes Instance ID
     * The app is restored on a new device
     * The user uninstalls/reinstall the app
     * The user clears app data.
     */
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token:"+refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * Attempts to send FCM token to server
     * @param token : An unique FCM token for device
     */
    private void sendRegistrationToServer(String token) {
        // save the token or send it to server
    }
}