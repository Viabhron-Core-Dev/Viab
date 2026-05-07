package com.nexus.launcher.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

/**
 * NexusAccessibilityService: Handles global system actions.
 * Used for "Double Tap to Sleep", Screenshots, and Back/Home gestures.
 */
class NexusAccessibilityService : AccessibilityService() {

    companion object {
        const val ACTION_SCREENSHOT = "com.nexus.ACTION_SCREENSHOT"
        const val ACTION_SLEEP = "com.nexus.ACTION_SLEEP"
        const val ACTION_RECENTS = "com.nexus.ACTION_RECENTS"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action?.let { action ->
            when (action) {
                ACTION_SCREENSHOT -> takeScreenshot()
                ACTION_SLEEP -> performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
                ACTION_RECENTS -> performGlobalAction(GLOBAL_ACTION_RECENTS)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun takeScreenshot() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            performGlobalAction(GLOBAL_ACTION_TAKE_SCREENSHOT)
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Monitor for WhatsApp calls or System overlays could go here
    }

    override fun onInterrupt() {}
}
