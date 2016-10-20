package net.paulboocock.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.logging.Handler;

public class ReceiveBroadcast extends BroadcastReceiver {

    public ReceiveBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Broadcast Received");
        Toast.makeText(context, "Broadcast Received", Toast.LENGTH_LONG).show();

        //Update UI Here
        MainActivity.getInstace().updateUI();
    }
}
