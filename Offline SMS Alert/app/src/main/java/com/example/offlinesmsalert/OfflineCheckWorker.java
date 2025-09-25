package com.example.offlinesmsalert;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class OfflineCheckWorker extends Worker {

    private static final String EMERGENCY_NUMBER = "+911234567890"; // Replace with actual number

    public OfflineCheckWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        boolean hasInternet = NetworkUtils.isInternetAvailable();

        if (!hasInternet) {
            String message = "Emergency Alert: Device has no internet connection.";
            SmsSender.sendSMS(getApplicationContext(), EMERGENCY_NUMBER, message);
        }

        return Result.success();
    }
}
