package com.github.mag0716.androidlint.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Android Resources Validation
 */
class TestService extends Service {
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
