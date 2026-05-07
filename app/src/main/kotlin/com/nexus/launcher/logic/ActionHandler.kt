package com.nexus.launcher.logic

import android.content.Context
import android.media.AudioManager
import android.provider.Settings
import com.nexus.launcher.data.NexusItem

/**
 * ActionHandler: Processes system-level toggles and sliders.
 * Centralized logic for both Launcher and Sidebar.
 */
class ActionHandler(private val context: Context) {

    fun execute(item: NexusItem) {
        when (item.action) {
            "toggle_volume" -> toggleMute()
            "screenshot" -> triggerScreenshot()
            "sleep" -> lockScreen()
            "accessibility_reset" -> openAccessibilitySettings()
        }
    }

    private fun toggleMute() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val filter = AudioManager.ADJUST_TOGGLE_MUTE
            audioManager.adjustStreamVolume(AudioManager.STREAM_RING, filter, 0)
        }
    }

    private fun openAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun triggerScreenshot() {
        // This usually requires sending a broadcast to our Accessibility Service
        val intent = Intent("com.nexus.launcher.ACTION_SCREENSHOT")
        context.sendBroadcast(intent)
    }

    private fun lockScreen() {
        val intent = Intent("com.nexus.launcher.ACTION_SLEEP")
        context.sendBroadcast(intent)
    }
}
