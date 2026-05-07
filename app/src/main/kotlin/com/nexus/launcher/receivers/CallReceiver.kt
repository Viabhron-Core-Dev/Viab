package com.nexus.launcher.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.nexus.launcher.services.CallRecordingService

/**
 * CallReceiver: Detects phone state changes to trigger recording.
 */
class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) ?: "unknown"

            when (state) {
                TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                    // Call answered/started
                    val serviceIntent = Intent(context, CallRecordingService::class.java).apply {
                        action = "START_RECORDING"
                        putExtra("PHONE_NUMBER", number)
                    }
                    context.startService(serviceIntent)
                }
                TelephonyManager.EXTRA_STATE_IDLE -> {
                    // Call ended
                    val serviceIntent = Intent(context, CallRecordingService::class.java).apply {
                        action = "STOP_RECORDING"
                    }
                    context.startService(serviceIntent)
                }
            }
        }
    }
}
