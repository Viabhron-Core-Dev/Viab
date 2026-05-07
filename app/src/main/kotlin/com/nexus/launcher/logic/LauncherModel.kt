package com.nexus.launcher.logic

import android.content.Context
import com.nexus.launcher.data.NexusItem

/**
 * LauncherModel: The central data repository for the Nexus launcher.
 * Synchronizes state between Sidebar and Workspace.
 */
class LauncherModel private constructor(private val context: Context) {

    private var favorites = mutableListOf<NexusItem>()
    private var sidebarItems = mutableListOf<NexusItem>()

    companion object {
        @Volatile
        private var INSTANCE: LauncherModel? = null

        fun getInstance(context: Context): LauncherModel {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LauncherModel(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    fun getFavorites(): List<NexusItem> = favorites
    fun getSidebarItems(): List<NexusItem> = sidebarItems

    fun addToSidebar(item: NexusItem) {
        sidebarItems.add(item)
    }

    fun saveLayout() {
        // Persist to SharedPreferences or Room DB
    }
}
