package com.example.offlinesmsalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            PeriodicWorkRequest offlineCheckRequest =
                    new PeriodicWorkRequest.Builder(OfflineCheckWorker.class, 15, TimeUnit.MINUTES)
                            .build();

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                    "offlineCheck",
                    ExistingPeriodicWorkPolicy.REPLACE,
                    offlineCheckRequest
            );
        }
    }
}
