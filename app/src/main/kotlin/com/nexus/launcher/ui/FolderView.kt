package com.nexus.launcher.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexus.launcher.R
import com.nexus.launcher.data.NexusItem
import com.nexus.launcher.ui.adapters.LauncherAdapter

/**
 * FolderView: A shared component for folder expansions on Home and Sidebar.
 */
class FolderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val recyclerView: RecyclerView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_folder, this, true)
        recyclerView = findViewById(R.id.folder_recycler)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
    }

    fun bind(folder: NexusItem) {
        recyclerView.adapter = LauncherAdapter(folder.children, {
            // Click App in folder
        }, { _, _ ->
            // Long click in folder
        })
    }
}
