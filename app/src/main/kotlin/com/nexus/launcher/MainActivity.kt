package com.nexus.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexus.launcher.services.NexusCoreService
import com.nexus.launcher.ui.adapters.LauncherAdapter
import com.nexus.launcher.logic.AppLoader

class MainActivity : AppCompatActivity() {
    
    private lateinit var workspace: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Start the persistence core immediately
        val serviceIntent = Intent(this, NexusCoreService::class.java)
        startService(serviceIntent)
        
        setupWorkspace()
    }

    private fun setupWorkspace() {
        workspace = findViewById(R.id.desktop_grid)
        workspace.layoutManager = GridLayoutManager(this, 4)
        
        // Load initial "Favorites" or workspace items
        val apps = AppLoader(this).loadAllApps().take(8) // Just a mock for now
        workspace.adapter = LauncherAdapter(apps, { item ->
            // Launch activity
            val intent = packageManager.getLaunchIntentForPackage(item.packageName!!)
            startActivity(intent)
        }, { item, view ->
            // Enter edit mode
            (workspace.adapter as LauncherAdapter).setEditMode(true)
        })
    }
}
