package com.nexus.launcher.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * PackageReceiver: Updates the launcher data when apps are installed/removed.
 */
class PackageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val packageName = intent.data?.schemeSpecificPart
        
        if (action == Intent.ACTION_PACKAGE_ADDED || 
            action == Intent.ACTION_PACKAGE_REMOVED || 
            action == Intent.ACTION_PACKAGE_REPLACED) {
            
            // Trigger a refresh in the LauncherModel (to be implemented)
            // This ensures the App Drawer stays in sync
        }
    }
}
