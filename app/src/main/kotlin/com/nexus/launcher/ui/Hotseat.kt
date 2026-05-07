package com.nexus.launcher.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nexus.launcher.R

/**
 * Hotseat: The dock area.
 * Holds up to 5 favorite apps/folders.
 */
class Hotseat @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        setBackgroundResource(R.color.nexus_glass)
        // Load favorites from LauncherModel
    }

    fun bindFavorites(items: List<NexusItem>) {
        removeAllViews()
        // Inflate item_launcher_app for each favorite
    }
}
