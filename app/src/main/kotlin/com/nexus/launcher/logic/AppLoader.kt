package com.nexus.launcher.logic

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import com.nexus.launcher.data.ItemType
import com.nexus.launcher.data.NexusItem

/**
 * AppLoader: Scans the system for installed applications.
 * Optimized for 3GB RAM devices by minimizing object allocation.
 */
class AppLoader(private val context: Context) {

    fun loadAllApps(): List<NexusItem> {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        
        val apps = context.packageManager.queryIntentActivities(mainIntent, 0)
        return apps.map { resolveInfo ->
            NexusItem(
                id = "${resolveInfo.activityInfo.packageName}/${resolveInfo.activityInfo.name}",
                label = resolveInfo.loadLabel(context.packageManager).toString(),
                packageName = resolveInfo.activityInfo.packageName,
                className = resolveInfo.activityInfo.name,
                type = ItemType.APP
            )
        }.sortedBy { it.label.lowercase() }
    }
}
