package ro.pub.cs.systems.eim.practicaltest01var06.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ro.pub.cs.systems.eim.practicaltest01var06.general.Constants;

public class PracticalTest01Var06Service extends Service {
    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int score = intent.getIntExtra(Constants.SERVICE_SCORE, -1);

        processingThread = new ProcessingThread(this, score);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}