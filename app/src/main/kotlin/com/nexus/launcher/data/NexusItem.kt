package com.nexus.launcher.data

import android.graphics.drawable.Drawable

/**
 * Core model for any element that can be displayed in the Launcher or Sidebar.
 */
data class NexusItem(
    val id: String,
    val label: String,
    val packageName: String? = null,
    val className: String? = null,
    val type: ItemType,
    val iconRes: Int = 0,
    var color: Int = 0xFF333333.toInt(),
    val action: String? = null,
    val children: MutableList<NexusItem> = mutableListOf() // For Folders
)

enum class ItemType {
    APP,
    SHORTCUT,
    FOLDER,
    SYSTEM_ACTION,
    WIDGET,
    LINK
}
