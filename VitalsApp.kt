package com.example.vitals

import android.app.Application
import androidx.work.*
import com.example.vitals.worker.ReminderWorker
import java.util.concurrent.TimeUnit

class VitalsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val request = PeriodicWorkRequestBuilder<ReminderWorker>(5, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "VitalsReminder",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
