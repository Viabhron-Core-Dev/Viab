package com.nexus.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nexus.launcher.services.NexusCoreService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Start the persistence core immediately
        val serviceIntent = Intent(this, NexusCoreService::class.java)
        startService(serviceIntent)
        
        // Setup Launcher UI (Reference to your React Mockup design)
        // Here we would inflate the fragment for the Desktop
        setContentView(R.layout.activity_main)
    }
}
