package ro.pub.cs.systems.eim.practicaltest01var06.service;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var06.general.Constants;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;
    private int score;

    public ProcessingThread(Context context, int score) {
        this.context = context;
        this.isRunning = true;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());

        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date("Victory " + System.currentTimeMillis()) + " " + score);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}