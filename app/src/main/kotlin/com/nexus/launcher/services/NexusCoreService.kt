package com.nexus.launcher.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.net.TrafficStats
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat

/**
 * NexusCoreService: The persistent background service.
 * Handles NetSpeed monitoring and ensures Process survival.
 */
class NexusCoreService : Service() {

    private val CHANNEL_ID = "nexus_core_service"
    private val NOTIFICATION_ID = 1337
    private val handler = Handler(Looper.getMainLooper())
    private var lastRxBytes: Long = 0
    private var lastTxBytes: Long = 0

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification("Initializing Nexus..."))
        startNetSpeedMonitoring()
    }

    private fun startNetSpeedMonitoring() {
        lastRxBytes = TrafficStats.getTotalRxBytes()
        lastTxBytes = TrafficStats.getTotalTxBytes()

        handler.post(object : Runnable {
            override fun run() {
                val currentRx = TrafficStats.getTotalRxBytes()
                val currentTx = TrafficStats.getTotalTxBytes()
                
                val rxSpeedKb = (currentRx - lastRxBytes) / 1024
                val txSpeedKb = (currentTx - lastTxBytes) / 1024
                
                updateNotification("▼ $rxSpeedKb KB/s  ▲ $txSpeedKb KB/s")
                
                lastRxBytes = currentRx
                lastTxBytes = currentTx
                
                // Poll every 2 seconds to save battery on 3GB RAM devices
                handler.postDelayed(this, 2000)
            }
        })
    }

    private fun createNotification(content: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Nexus Native Core")
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(content: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, createNotification(content))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Nexus Performance Utility",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
