package com.example.offlinesmsalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeriodicWorkRequest offlineCheckRequest =
                        new PeriodicWorkRequest.Builder(OfflineCheckWorker.class, 15, TimeUnit.MINUTES)
                                .build();

                WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork(
                        "offlineCheck",
                        ExistingPeriodicWorkPolicy.REPLACE,
                        offlineCheckRequest
                );
            }
        });
    }
}
